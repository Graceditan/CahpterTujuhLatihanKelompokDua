package com.example.cahptertujuhlatihankelompokdua.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class GetMenuItem(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("harga")
    val harga: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("nama_makanan")
    val namaMakanan: String,
    @SerializedName("deskripsi")
    val desc: String,
    @SerializedName("gambar")
    val gambar: String
): Parcelable