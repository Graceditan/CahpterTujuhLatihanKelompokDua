package com.example.cahptertujuhlatihankelompokdua.view

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.cahptertujuhlatihankelompokdua.R
import com.example.cahptertujuhlatihankelompokdua.model.GetUserItem
import com.example.cahptertujuhlatihankelompokdua.viewmodel.ViewModelUser
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    lateinit var dataUser : List<GetUserItem>
    lateinit var viewModel : ViewModelUser
    lateinit var email: String
    lateinit var password: String
    lateinit var toast : String
    lateinit var userManager : com.example.cahptertujuhlatihankelompokdua.data.UserManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.fragment_login, container, false)
        val daftar = view.findViewById<TextView>(R.id.login_daftar)
        val login = view.findViewById<Button>(R.id.login_button)
        getDataUserItem()
        daftar.setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        login.setOnClickListener {
            if (login_email.text.isNotEmpty() && login_password.text.isNotEmpty()){
                email = login_email.text.toString()
                password = login_password.text.toString()
                check(dataUser)

            }
            else{
                toast = "Harap Isi Semua Data"
                customToast()
            }
        }
        return view
    }


    fun getDataUserItem(){
        viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewModel.getLiveUserObserver().observe(viewLifecycleOwner, Observer {
            dataUser = it

        })
        viewModel.userApi()
    }

    fun check(dataUser : List<GetUserItem>) {
        userManager = com.example.cahptertujuhlatihankelompokdua.data.UserManager(requireContext())
        for (i in dataUser.indices) {
            if (email == dataUser[i].email && password == dataUser[i].password) {
                GlobalScope.launch {

                    userManager.saveDataLogin("true")
                    userManager.saveDataUser(dataUser[i].id, dataUser[i].email, dataUser[i].password, dataUser[i].username)
                }
                loginNew(email, password)
                view?.findNavController()
                    ?.navigate(R.id.action_loginFragment_to_homeFragment)
                break
            }
        }
    }

    fun loginNew(email :String, password : String){
        viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewModel.getLiveLoginObserver().observe(requireActivity(), Observer {
            if (it  == null){
                Toast.makeText(requireContext(), "Gagal Login", Toast.LENGTH_LONG ).show()
            }else{
                Toast.makeText(requireContext(), "Berhasil Login", Toast.LENGTH_LONG ).show()
            }
        })
        viewModel.login(email)
    }

    fun customToast(){
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
        toast.setGravity(Gravity.CENTER or Gravity.TOP, 0, 960)
    }

}