package com.example.apz_mobile.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.apz_mobile.R
import com.example.apz_mobile.models.Medication

class MedicationsAdapter() : BaseViewAdapter<Medication>() {

    override fun getViewHolderByViewType(
        inflater: LayoutInflater,
        viewType: Int,
        parent: ViewGroup
    ) =
        MedicationsViewHolder(
            inflater.inflate(R.layout.medication_item, parent, false)
        )
}