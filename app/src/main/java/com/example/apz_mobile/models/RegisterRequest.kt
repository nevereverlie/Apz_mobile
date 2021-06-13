package com.example.apz_mobile.models

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("firstname") val firstname: String,
    @SerializedName("lastname") val lastname: String
)