package com.example.apz_mobile.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.apz_mobile.R
import com.example.apz_mobile.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : BaseActivity<SignInActivity, LoginPresenter>() {

    override val presenter = LoginPresenter()

    override val layoutResId = R.layout.activity_sign_in

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btnLogin.setOnClickListener {
            presenter.onLoginClicked(
                tvLoginEmail.text.toString(),
                tvLoginPassword.text.toString()
            )
        }

        tvSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        if (getSharedPreferences("myPrefs", Context.MODE_PRIVATE).getInt("userId", -1) != -1) {
            navigateToMainScreen()
        }
    }

    fun onLoginError() {
        Toast.makeText(this, "Невірний e-mail або пароль", Toast.LENGTH_LONG)
            .show()
    }

    fun onLoginSuccess(userId: Int, token: String) {
        val sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt("userId", userId)
            putString("token", token)
            apply()
        }

        navigateToMainScreen()
    }

    private fun navigateToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}