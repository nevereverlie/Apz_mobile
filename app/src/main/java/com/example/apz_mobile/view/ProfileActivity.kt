package com.example.apz_mobile.view

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.apz_mobile.R
import com.example.apz_mobile.models.User
import com.example.apz_mobile.presenter.ProfilePresenter
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : BaseActivity<ProfileActivity, ProfilePresenter>() {

    override val layoutResId = R.layout.activity_profile

    override val presenter = ProfilePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ivMedications.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            val builder = AlertDialog.Builder(this)

            builder.setTitle(getString(R.string.logout_dialog_title))

            builder.setMessage(getString(R.string.logout_dialog_message))

            builder.setPositiveButton(getString(R.string.logout_dialog_btn_positive)){dialog, which ->
                val sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putInt("userId", -1)
                    apply()
                }

                val intent = Intent(this, SignInActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)

                Toast.makeText(applicationContext,getString(R.string.logout_dialog_success),Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton(getString(R.string.logout_dialog_btn_negative)){dialog,which -> }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        presenter.loadUserData(
            getSharedPreferences(
                "myPrefs",
                Context.MODE_PRIVATE
            ).getInt("userId", -1)
        )
    }

    fun showUserInfo(user: User) {
        etFirstname.setText(user.firstname)
        etLastname.setText(user.lastname)
        etEmail.setText(user.userEmail)
    }

    fun showMedicationsCount(count: Int) {
        tvProfileMedicationsCount.text = count.toString()
    }
}