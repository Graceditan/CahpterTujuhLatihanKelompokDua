package com.example.cahptertujuhlatihankelompokdua.view

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.cahptertujuhlatihankelompokdua.R
import com.example.cahptertujuhlatihankelompokdua.model.GetUserItem
import com.example.cahptertujuhlatihankelompokdua.model.UpdateResponse
import com.example.cahptertujuhlatihankelompokdua.viewmodel.ViewModelUser
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    lateinit var regisemailtext: String

    lateinit var dataUser: List<GetUserItem>
    lateinit var viewModel: ViewModelUser
    lateinit var password: String
    lateinit var toast: String
    lateinit var register: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_register, container, false)
        getDataUserItem()
        register = "true"

        view.register_button.setOnClickListener {
            val username = register_username.text.toString()
            regisemailtext = register_email.text.toString()
            password = register_password.text.toString()
            val confirmpass = register_konfirmasipassword.text.toString()

            if (register_username.text.isNotEmpty() && register_email.text.isNotEmpty()
                && register_password.text.isNotEmpty()
                && register_konfirmasipassword.text.isNotEmpty()
            ) {
                if (password == confirmpass) {
                    for (i in dataUser.indices) {
                        if (regisemailtext == dataUser[i].email) {
                            register = "false"
                            break
                        } else {
                            register = "true"
                        }
                    }

                    if (register == "true") {
                        regisUser(username, regisemailtext, password)
                        view.findNavController()
                            .navigate(R.id.action_registerFragment_to_loginFragment)
                    } else {
                        toast = "Email Sudah Terdaftar"
                        customToast()
                    }

                } else {
                    toast = "Konfirmasi Password Tidak Sesuai"
                    customToast()
                }
            } else {
                toast = "Harap isi semua data"
                customToast()
            }
        }
        return view
    }


    fun regisUser(username: String, email: String, password: String) {
        viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewModel.getLiveRegisObserver().observe(requireActivity(), Observer {
            if (it  == null){
                Toast.makeText(requireContext(), "Gagal Register", Toast.LENGTH_LONG ).show()
            }else{
                Toast.makeText(requireContext(), "Berhasil Register", Toast.LENGTH_LONG ).show()
            }
        })
        viewModel.regisUser(username,email,password)
    }

    fun getDataUserItem() {
        viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewModel.getLiveUserObserver().observe(viewLifecycleOwner, Observer {
            dataUser = it
        })
        viewModel.userApi()
    }

    fun customToast() {
        val text = toast
        val toast = Toast.makeText(
            requireActivity()?.getApplicationContext(),
            text,
            Toast.LENGTH_LONG
        )
        val text1 =
            toast.getView()?.findViewById(android.R.id.message) as TextView
        val toastView: View? = toast.getView()
        toastView?.setBackgroundColor(Color.TRANSPARENT)
        text1.setTextColor(Color.RED);
        text1.setTextSize(15F)
        toast.show()
        toast.setGravity(Gravity.CENTER or Gravity.TOP, 0, 1420)
    }
}

