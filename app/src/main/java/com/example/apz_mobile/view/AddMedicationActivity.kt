package com.example.apz_mobile.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.apz_mobile.R
import com.example.apz_mobile.models.Medication
import com.example.apz_mobile.presenter.AddMedicationPresenter
import kotlinx.android.synthetic.main.activity_add_medication.*
import kotlinx.android.synthetic.main.activity_add_medication.ivMedications
import kotlinx.android.synthetic.main.activity_add_medication.ivProfile
import kotlinx.android.synthetic.main.activity_main.*

class AddMedicationActivity: BaseActivity<AddMedicationActivity, AddMedicationPresenter>() {
    override val layoutResId = R.layout.activity_add_medication

    override val presenter = AddMedicationPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ivMedications.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        ivProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        btnAddMedication.setOnClickListener {
            val medicationToAdd = Medication(
                    medicationId = 0,
                    medicineName = etAddMedicineName.text.toString(),
                    medicationType = etAddMedicationType.text.toString(),
                    medicationAmount = Integer.parseInt(
                        etAddMedicationAmount.text.toString(), 10),
                    medicationTime = etAddMedicationTime.text.toString(),
                    userId = getSharedPreferences(
                        "myPrefs",
                        Context.MODE_PRIVATE
                    ).getInt("userId", -1))

            addMedication(medicationToAdd)
        }
    }

    private fun addMedication(medicationToAdd: Medication) {
        presenter.addMedication(medicationToAdd)
    }

    fun onSuccess() {
        Toast.makeText(this, "Прийом успішно додано!",
            Toast.LENGTH_LONG)
            .show()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun medicationAddingError() {
        Toast.makeText(this, "Не вдалося додати прийом...",
            Toast.LENGTH_LONG)
            .show()
    }
}