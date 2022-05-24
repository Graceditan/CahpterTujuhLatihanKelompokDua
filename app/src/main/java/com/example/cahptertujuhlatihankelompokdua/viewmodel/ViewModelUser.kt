package com.example.cahptertujuhlatihankelompokdua.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cahptertujuhlatihankelompokdua.model.GetUserItem
import com.example.cahptertujuhlatihankelompokdua.model.UpdateResponse
import com.example.cahptertujuhlatihankelompokdua.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ViewModelUser : ViewModel(){

    var liveDataNewUserItem : MutableLiveData<List<GetUserItem>> = MutableLiveData()
    var liveDataLogin : MutableLiveData<List<GetUserItem>> = MutableLiveData()
    var liveDataRegis : MutableLiveData<GetUserItem> = MutableLiveData()
    var liveDataUpdate : MutableLiveData<GetUserItem> = MutableLiveData()

    fun getLiveUserObserver() : MutableLiveData<List<GetUserItem>> {
        return liveDataNewUserItem
    }

    fun getLiveLoginObserver() : MutableLiveData<List<GetUserItem>> {
        return liveDataNewUserItem
    }

    fun getLiveRegisObserver() : MutableLiveData<GetUserItem> {
        return liveDataRegis
    }

    fun getLiveUpdateObserver() : MutableLiveData<GetUserItem> {
        return liveDataUpdate
    }




    fun userApi(){
        ApiClient.instance.getUser()
            .enqueue(object : retrofit2.Callback<List<GetUserItem>>{
                override fun onResponse(
                    call: Call<List<GetUserItem>>,
                    getAllItem: Response<List<GetUserItem>>
                ) {
                    if (getAllItem.isSuccessful){
                        liveDataNewUserItem.postValue(getAllItem.body())

                    }else{
                        liveDataNewUserItem.postValue(null)

                    }
                }
                override fun onFailure(call: Call<List<GetUserItem>>, t: Throwable) {
                    liveDataNewUserItem.postValue(null)
                }
            })
    }

    fun login(email :String){
        ApiClient.instance.Login(email).enqueue(object :
            Callback<List<GetUserItem>> {
            override fun onResponse(
                call: Call<List<GetUserItem>>,
                response: Response<List<GetUserItem>>
            )
            {
                if (response.isSuccessful){
                    liveDataLogin.postValue(response.body())

                }else{
                    liveDataLogin.postValue(null)
                }
            }
            override fun onFailure(call: Call<List<GetUserItem>>, t: Throwable) {
                liveDataLogin.postValue(null)

            }
        })
    }


    fun regisUser(username: String, email: String, password: String) {
        ApiClient.instance.registerNew(username, email, password)
            .enqueue(object : Callback<GetUserItem> {
                override fun onResponse(
                    call: Call<GetUserItem>,
                    response: Response<GetUserItem>
                ) {
                    if (response.isSuccessful) {
                        liveDataRegis.postValue(response.body())
                    } else {
                        liveDataRegis.postValue(null)
                    }
                }

                override fun onFailure(call: Call<GetUserItem>, t: Throwable) {
                    liveDataRegis.postValue(null)
                }
            })
    }

    fun updateDataUser(id : Int, dataUser : UpdateResponse){
        ApiClient.instance.updateNewUser(dataUser, id.toString() )
            .enqueue(object : retrofit2.Callback<GetUserItem> {
                override fun onResponse(
                    call: Call<GetUserItem>,
                    response: Response<GetUserItem>
                ) {
                    liveDataUpdate.postValue(response.body())

                }
                override fun onFailure(call: Call<GetUserItem>, t: Throwable) {
                    liveDataUpdate.postValue(null)
                }
            })
    }

}