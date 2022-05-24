package com.example.cahptertujuhlatihankelompokdua.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UpdateMenuResponse(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("harga")
    val harga: String,
    @SerializedName("nama_makanan")
    val namaMakanan: String,
    @SerializedName("deskripsi")
    val desc: String,
    @SerializedName("gambar")
    val gambar: String
): Parcelable

