package com.example.apz_mobile.view

import androidx.recyclerview.widget.DiffUtil

open class BaseDiffCallBack<T>(protected val oldList: List<T>, protected val newList: List<T>) :
    DiffUtil.Callback() {

    final override fun getOldListSize() = oldList.size

    final override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}