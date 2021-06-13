package com.example.apz_mobile.presenter

import com.example.apz_mobile.models.LoginResponse
import com.example.apz_mobile.repository.UserRepository
import com.example.apz_mobile.view.SignInActivity
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

class LoginPresenter : BasePresenter<SignInActivity>() {

    private val userRepository = UserRepository()

    fun onLoginClicked(name: String, password: String) {
        userRepository
            .loginUser(name, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onLoginSuccess, ::onError)
    }

    private fun onLoginSuccess(loginResponse: LoginResponse) {
        view?.onLoginSuccess(loginResponse.userId, loginResponse.token)
    }

    override fun onError(error: Throwable) {
        super.onError(error)

        view?.onLoginError()
    }
}