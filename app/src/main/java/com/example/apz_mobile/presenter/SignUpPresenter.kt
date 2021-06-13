package com.example.apz_mobile.presenter

import android.util.Log
import android.widget.Toast
import com.example.apz_mobile.models.LoginResponse
import com.example.apz_mobile.repository.UserRepository
import com.example.apz_mobile.view.SignUpActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignUpPresenter : BasePresenter<SignUpActivity>() {

    private val userRepository = UserRepository()

    private var currentEmail: String = ""
    private var currentPassword: String = ""

    fun onSignUpClicked(email: String, password: String, firstname: String, lastname: String) {
        currentEmail = email
        currentPassword = password

        if (email != "" && password != "") {
            userRepository
                .registerUser(email, password, firstname, lastname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::onSignUpSuccess, ::onError)
        } else {
            view?.emptyError()
        }
    }

    private fun onSignUpSuccess() {
        userRepository
            .loginUser(currentEmail, currentPassword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onLoginSuccess, ::onError)
    }

    private fun onLoginSuccess(response: LoginResponse) {
        view?.onSignUpSuccess(response.userId, response.token)
    }

    override fun onError(error: Throwable) {
        Log.wtf("regError", error);
        super.onError(error)
        view?.onSigUpError()
    }
}