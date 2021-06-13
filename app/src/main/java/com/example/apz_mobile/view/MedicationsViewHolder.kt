package com.example.apz_mobile.view

import android.util.Log
import android.view.View
import com.example.apz_mobile.models.Medication
import kotlinx.android.synthetic.main.medication_item.view.*
import java.util.*

class MedicationsViewHolder(view: View): BaseViewAdapter.ViewHolder<Medication>(view) {
    init {
        //itemView.setOnClickListener { itemClickListener.invoke(savedData!!) }
        //itemView.addView.setOnClickListener{ itemClickListener2.invoke(savedData!!)}
    }

    override fun setContent(data: Medication) {
        itemView.tvMedicineName.setText(data.medicineName)
        itemView.tvMedicationAmount.setText(data.medicationAmount.toString())
        itemView.tvMedicationType.setText(data.medicationType)
        itemView.tvMedicationTime.setText(data.medicationTime)
        itemView.tvMedicationId.setText(data.medicationId.toString())

       Log.wtf("sadasd", Locale.getDefault().displayLanguage)
    }
}