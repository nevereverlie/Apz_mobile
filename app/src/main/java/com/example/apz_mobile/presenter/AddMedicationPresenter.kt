package com.example.apz_mobile.presenter

import com.example.apz_mobile.models.Medication
import com.example.apz_mobile.repository.MedicationRepository
import com.example.apz_mobile.view.AddMedicationActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AddMedicationPresenter: BasePresenter<AddMedicationActivity>() {
    private val medicationRepository = MedicationRepository()

    fun addMedication(medication: Medication) {
        medicationRepository
                .addMedication(medication)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::onSuccess, ::onError)
    }

    private fun onSuccess() {
        view?.onSuccess()
    }

    override fun onError(error: Throwable) {
        super.onError(error)
        view?.medicationAddingError()
    }
}