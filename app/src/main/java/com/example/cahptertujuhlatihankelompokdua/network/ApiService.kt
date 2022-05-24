package com.example.cahptertujuhlatihankelompokdua.network

import com.example.cahptertujuhlatihankelompokdua.model.GetMenuItem
import com.example.cahptertujuhlatihankelompokdua.model.GetUserItem
import com.example.cahptertujuhlatihankelompokdua.model.UpdateMenuResponse
import com.example.cahptertujuhlatihankelompokdua.model.UpdateResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("user")
    fun getUser(): Call<List<GetUserItem>>

    @GET("menu-restoran")
    fun getMenu(): Call<List<GetMenuItem>>

    @POST("user")
    @FormUrlEncoded
    fun registerNew(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<GetUserItem>


    @GET("user")
    fun Login(@Query("email") email : String) : Call<List<GetUserItem>>

    @PUT("user/{id}")
    fun updateNewUser(
        @Body user : UpdateResponse, @Path("id") id : String
    ): Call<GetUserItem>

    @PUT("menu-restoran/{id}")
    fun updateMenu(
        @Body menu : UpdateMenuResponse, @Path("id") id : String
    ): Call<GetMenuItem>




}