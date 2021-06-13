package com.example.apz_mobile.models

import com.google.gson.annotations.SerializedName

data class Medication(
        @SerializedName("medicationId") val medicationId: Int,
        @SerializedName("medicineName") val medicineName: String,
        @SerializedName("medicationAmount") val medicationAmount: Int,
        @SerializedName("medicationType") val medicationType: String,
        @SerializedName("medicationTime") val medicationTime: String,
        @SerializedName("userId") val userId: Int
)