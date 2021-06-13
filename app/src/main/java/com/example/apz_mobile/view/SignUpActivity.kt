package com.example.apz_mobile.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Toast
import com.example.apz_mobile.R
import com.example.apz_mobile.presenter.SignUpPresenter
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity() : BaseActivity<SignUpActivity, SignUpPresenter>() {

    override val presenter = SignUpPresenter()

    override val layoutResId = R.layout.activity_sign_up


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btnSignUp.setOnClickListener {
            presenter.onSignUpClicked(
                tvRegisterEmail.text.toString(),
                tvRegisterPassword.text.toString(),
                tvRegisterFirstname.text.toString(),
                tvRegisterLastname.text.toString()
            )
        }

        tvSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }

    fun onSigUpError() {
        Toast.makeText(this, "Користувач з такими даними вже існує!", Toast.LENGTH_LONG).show()
    }
    fun emptyError() {
        Toast.makeText(this, "Будь ласка, заповніть всі поля", Toast.LENGTH_LONG).show()
    }

    fun onSignUpSuccess(userId: Int, token: String) {
        val sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt("userId", userId)
            putString("token", token)
            apply()
        }

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}