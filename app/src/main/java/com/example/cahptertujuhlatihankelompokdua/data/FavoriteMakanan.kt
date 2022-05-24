package com.example.cahptertujuhlatihankelompokdua.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Fav")
@Parcelize
data class FavoriteMakanan(
    @PrimaryKey(autoGenerate = true)
    val idfav: Int?,
    @ColumnInfo(name = "email")
    val email: String?,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("harga")
    val harga: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("nama_makanan")
    val namaMakanan: String,
    @SerializedName("gambar")
    val gambar: String,
    @SerializedName("deskripsi")
    val desc: String
): Parcelable
