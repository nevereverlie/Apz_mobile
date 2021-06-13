package com.example.apz_mobile.presenter

import com.example.apz_mobile.models.Medication
import com.example.apz_mobile.models.User
import com.example.apz_mobile.repository.MedicationRepository
import com.example.apz_mobile.repository.UserRepository
import com.example.apz_mobile.view.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter : BasePresenter<MainActivity>() {

    private val medicationRepository = MedicationRepository()

    fun loadMedications(userId: Int) {
        medicationRepository
            .getMedications(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onLoadSuccess, ::onLoadError)
    }

    private fun onLoadSuccess(medications: List<Medication>) {
        view?.showMedications(medications)
    }

    private fun onLoadError(error: Throwable) {
        super.onError(error)
        view?.showMedicationsLoadError()
    }
}