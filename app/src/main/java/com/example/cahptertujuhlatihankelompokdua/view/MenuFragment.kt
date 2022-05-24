package com.example.cahptertujuhlatihankelompokdua.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.cahptertujuhlatihankelompokdua.R
import com.example.cahptertujuhlatihankelompokdua.data.FavoriteMakanan
import com.example.cahptertujuhlatihankelompokdua.model.GetMenuItem
import com.example.cahptertujuhlatihankelompokdua.model.UpdateMenuResponse
import com.example.cahptertujuhlatihankelompokdua.viewmodel.ViewModelMakanan
import kotlinx.android.synthetic.main.fragment_menu.view.*

class MenuFragment :  Fragment() {
    lateinit var id : String
    lateinit var nama : String
    lateinit var harga : String
    lateinit var desc : String
    lateinit var gambar : String
    lateinit var viewModel: ViewModelMakanan
    lateinit var dataUser : UpdateMenuResponse
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        val getmakanan = arguments?.getParcelable<GetMenuItem>("detailfilm")
        val getmakananupdate = arguments?.getParcelable<UpdateMenuResponse>("updatemenu")
        val getmakananfromfav = arguments?.getParcelable<FavoriteMakanan>("detailfilmfromfav")

        if (getmakananfromfav != null && getmakananupdate == null){
            view.menu1_namamakanan.setText ( getmakananfromfav.namaMakanan)
            view.menu2_hargamakanan.setText ( getmakananfromfav.harga )
            view.menu3_linkgambar.setText ( getmakananfromfav.gambar )
            view.menu4_deskripsi.setText ( getmakananfromfav.desc )
            Glide.with(requireContext()).load(getmakananfromfav.gambar).into(view.menu_image)
            id = getmakananfromfav.id

        }
        if (getmakanan != null && getmakananupdate == null){
            view.menu1_namamakanan.setText ( getmakanan.namaMakanan)
            view.menu2_hargamakanan.setText ( getmakanan.harga )
            view.menu3_linkgambar.setText ( getmakanan.gambar )
            view.menu4_deskripsi.setText ( getmakanan.desc )
            Glide.with(requireContext()).load(getmakanan.gambar).into(view.menu_image)
            id = getmakanan.id

        }

        if (getmakananupdate!=null){
            view.menu1_namamakanan.setText ( getmakananupdate.namaMakanan)
            view.menu2_hargamakanan.setText ( getmakananupdate.harga )
            view.menu3_linkgambar.setText ( getmakananupdate.gambar )
            view.menu4_deskripsi.setText ( getmakananupdate.desc )
            Glide.with(requireContext()).load(getmakananupdate.gambar).into(view.menu_image)
            id = getmakananupdate.id.toString()
        }

        view.menu_buttonupdate.setOnClickListener {
            nama = view.menu1_namamakanan.text.toString()
            harga  = view.menu2_hargamakanan.text.toString()
            gambar = view.menu3_linkgambar.text.toString()
            desc = view.menu4_deskripsi.text.toString()
            dataUser = UpdateMenuResponse(id.toInt(), harga, nama, desc, gambar)
            updateDataMenu(id.toInt(), dataUser)
            val bund = Bundle()
            bund.putParcelable("updatemenu", dataUser)

            view.findNavController().navigate(R.id.action_menuFragment_to_detailFragment, bund)
        }
        return view
    }

    fun updateDataMenu(id : Int, dataUser : UpdateMenuResponse){
        viewModel = ViewModelProvider(this).get(ViewModelMakanan::class.java)
        viewModel.getLiveMenuUpdateObserver().observe(requireActivity(), Observer {
            if (it  == null){
                Toast.makeText(requireContext(), "Gagal Update Data", Toast.LENGTH_LONG ).show()
            }else{
                Toast.makeText(requireContext(), "Berhasil Update Data", Toast.LENGTH_LONG ).show()
            }
        })
        viewModel.updateDataMenu(id, dataUser)
    }

}