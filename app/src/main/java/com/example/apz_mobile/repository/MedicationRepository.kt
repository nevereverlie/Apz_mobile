package com.example.apz_mobile.repository

import com.example.apz_mobile.models.LoginRequest
import com.example.apz_mobile.models.Medication

class MedicationRepository {
    private val apiService = ApiService.getWebService()

    fun getMedications(userId: Int) =
        apiService.getMedications(userId)

    fun addMedication(medication: Medication) =
        apiService.addMedication(medication)

    fun deleteMedication(medicationId: Int) =
        apiService.deleteMedication(medicationId)
}