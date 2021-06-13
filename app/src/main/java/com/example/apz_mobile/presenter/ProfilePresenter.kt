package com.example.apz_mobile.presenter

import android.util.Log
import com.example.apz_mobile.models.Medication
import com.example.apz_mobile.models.User
import com.example.apz_mobile.repository.MedicationRepository
import com.example.apz_mobile.repository.UserRepository
import com.example.apz_mobile.view.ProfileActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProfilePresenter : BasePresenter<ProfileActivity>() {

    private val userRepository = UserRepository()

    private val medicationRepository = MedicationRepository()

    private var currentUserId: Int = -1

    fun loadUserData(id: Int) {
        currentUserId = id

        userRepository
            .getUserById(currentUserId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onUserLoaded, ::onError)

        medicationRepository
            .getMedications(currentUserId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::onMedicationsLoaded, ::onError)
    }

    private fun onUserLoaded(user: User) {
        Log.wtf("asd", "User loaded, name: ${user.lastname} ${user.firstname}")

        view?.showUserInfo(user)
    }

    private fun onMedicationsLoaded(medications: List<Medication>) {
        view?.showMedicationsCount(medications.count())
    }

}