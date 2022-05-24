package com.example.cahptertujuhlatihankelompokdua.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UpdateResponse (
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("username")
    val username: String,
): Parcelable

