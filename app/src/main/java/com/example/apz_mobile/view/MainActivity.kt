package com.example.apz_mobile.view

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.apz_mobile.R
import com.example.apz_mobile.models.Medication
import com.example.apz_mobile.presenter.MainPresenter
import com.example.apz_mobile.repository.ApiService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainActivity, MainPresenter>() {
    override val layoutResId = R.layout.activity_main
    override val presenter = MainPresenter()

    private val adapter = MedicationsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ivProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        ivAddMedication.setOnClickListener {
            val intent = Intent(this, AddMedicationActivity::class.java)
            startActivity(intent)
        }

        ApiService.token = getSharedPreferences(
            "myPrefs",
            Context.MODE_PRIVATE
        ).getString("token", "").toString()

        initRvMedications()
    }

    private fun initRvMedications() {
        rvMedicationsList.adapter = adapter
        presenter.loadMedications(
            getSharedPreferences(
                "myPrefs",
                Context.MODE_PRIVATE
            ).getInt("userId", -1)
        )
    }

    fun showMedications(medications: List<Medication>) {
        adapter.updateList(medications)
    }

    fun showMedicationsLoadError() {
        Toast.makeText(this, "Не вдалося завантажити список...",
            Toast.LENGTH_LONG)
            .show()
    }
}