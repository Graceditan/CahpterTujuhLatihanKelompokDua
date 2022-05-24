package com.example.cahptertujuhlatihankelompokdua.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.cahptertujuhlatihankelompokdua.R
import com.example.cahptertujuhlatihankelompokdua.data.UserManager
import com.example.cahptertujuhlatihankelompokdua.viewmodel.ViewModelMakanan
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
    lateinit var adaptermakanan : AdapterHome

    lateinit var userManager : UserManager
    lateinit var email : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        var homelinear = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        userManager = com.example.cahptertujuhlatihankelompokdua.data.UserManager(requireContext())
        userManager.userImage.asLiveData().observe(requireActivity()){
            if (it !="x") {
                Glide.with(requireContext()).load(it).into(view.home_profile)
            }
        }
        adaptermakanan = AdapterHome(){
            val bund = Bundle()
            bund.putParcelable("detailfilm", it)
            view.findNavController().navigate(R.id.action_homeFragment_to_detailFragment,bund)

        }
        view.home_rv.adapter = adaptermakanan
        view.home_rv.layoutManager = homelinear

        getMakanan()
        view.home_profile.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
        userManager = UserManager(requireContext())
        userManager.userUsername.asLiveData().observe(requireActivity()){
            view.home_welcome.text = it.toString()
        }
        return view
    }

    fun getMakanan(){
        val viewModel = ViewModelProvider(requireActivity()).get(ViewModelMakanan::class.java)
        viewModel.getLiveMenuObserver().observe(requireActivity()) {
            if(it != null){
                adaptermakanan.setDataFilm(it)
                adaptermakanan.notifyDataSetChanged()
            }
        }
        viewModel.getMenu()
    }


}