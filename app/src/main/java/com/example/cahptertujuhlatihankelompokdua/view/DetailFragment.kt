package com.example.cahptertujuhlatihankelompokdua.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.cahptertujuhlatihankelompokdua.R
import com.example.cahptertujuhlatihankelompokdua.data.FavoriteDB
import com.example.cahptertujuhlatihankelompokdua.data.FavoriteMakanan
import com.example.cahptertujuhlatihankelompokdua.model.GetMenuItem
import com.example.cahptertujuhlatihankelompokdua.model.UpdateMenuResponse
import de.hdodenhof.circleimageview.BuildConfig
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlin.properties.Delegates


class DetailFragment : Fragment() {
    var db: FavoriteDB? = null
    var film : List<FavoriteMakanan>? = null

    lateinit var id : String
    lateinit var title : String
    lateinit var harga : String
    lateinit var email : String
    lateinit var createdAt : String
    lateinit var gambar : String
    lateinit var desc : String

    lateinit var userManager : com.example.cahptertujuhlatihankelompokdua.data.UserManager

    lateinit var toggleFavorite : String
    var alreadyFavorite by Delegates.notNull<Boolean>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        userManager = com.example.cahptertujuhlatihankelompokdua.data.UserManager(requireContext())
        val getfilm = arguments?.getParcelable<GetMenuItem>("detailfilm")
        val getfilmfromfav = arguments?.getParcelable<FavoriteMakanan>("detailfilmfromfav")
        val getUpdateMenu = arguments?.getParcelable<UpdateMenuResponse>("updatemenu")
        db = FavoriteDB.getInstance(requireActivity())

        if (getfilm != null){
            view.text1.text = getfilm.namaMakanan
            view.text2.text = getfilm.harga
            view.text4.text = getfilm.desc
            Glide.with(requireContext()).load(getfilm.gambar).into(view.detail_image)
            id = getfilm.id
            title = getfilm.namaMakanan
            harga = getfilm.harga
            createdAt = getfilm.createdAt
            gambar = getfilm.gambar
            desc = getfilm.desc
        }

        if (getfilmfromfav != null){
            view.text1.text = getfilmfromfav.namaMakanan
            view.text2.text = getfilmfromfav.harga
            view.text4.text = getfilmfromfav.desc
            Glide.with(requireContext()).load(getfilmfromfav.gambar).into(view.detail_image)
            id = getfilmfromfav.id
            title = getfilmfromfav.namaMakanan
            harga = getfilmfromfav.harga
            createdAt = getfilmfromfav.createdAt
            gambar = getfilmfromfav.gambar
            desc = getfilmfromfav.desc
        }

        if (getUpdateMenu!=null){
            id = getUpdateMenu.id.toString()
            view.text1.text = getUpdateMenu.namaMakanan
            view.text2.text = getUpdateMenu.harga
            view.text4.text = getUpdateMenu.desc
            Glide.with(requireContext()).load(getUpdateMenu.gambar).into(view.detail_image)
        }

        email = ""
        toggleFavorite = "false"
        alreadyFavorite = false

        userManager.userEmail.asLiveData().observe(requireActivity()){
            email = it
            GlobalScope.async {
                film = db?.getFavoriteDao()?.checkFav(email, id.toInt())
                val checkDB = film?.size !=0
                if (checkDB){
                    view.btnfavorite.setImageResource(R.drawable.fav_2)
                    toggleFavorite = "true"
                    alreadyFavorite = true
                }else {
                    view.btnfavorite.setImageResource(R.drawable.fav_1 )
                    toggleFavorite = "false"
                    alreadyFavorite = false
                }
            }
        }


        view.btnfavorite.setOnClickListener {
            toggleButton()
        }

        if(BuildConfig.FLAVOR.equals("admin"))
        {
            view.btnedit.setOnClickListener {
                val bund = Bundle()
                if (getfilmfromfav !=null && getUpdateMenu ==null){
                    bund.putParcelable("detailfilmfromfav", getfilmfromfav)
                }
                if (getfilm !=null && getUpdateMenu ==null){
                    bund.putParcelable("detailfilm", getfilm)
                }
                else if (getUpdateMenu!=null){
                    bund.putParcelable("updatemenu", getUpdateMenu)
                }

                view.findNavController().navigate(R.id.action_detailFragment_to_menuFragment,bund)
            }
        }

        view.btnbackhome.setOnClickListener {
            view.findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
        }

        return view
    }

    fun toggleButton(){
        for (data in toggleFavorite){
            if (toggleFavorite == "true"  ){
                btnfavorite.setImageResource(R.drawable.fav_1)
                toggleFavorite = "false"
                GlobalScope.async {
                    db?.getFavoriteDao()?.deleteMakananID(email, id.toInt())
                }
                break
            }

            if (toggleFavorite == "false"  ) {
                btnfavorite.setImageResource(R.drawable.fav_2)
                toggleFavorite= "true"
                GlobalScope.async {
                    db?.getFavoriteDao()?.addMakanan(
                        FavoriteMakanan(
                            null,
                            email,
                            createdAt,
                            harga,
                            id,
                            title,
                            gambar,
                            desc
                        )
                    )
                }
                break
            }
        }
    }

}