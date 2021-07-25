package ru.lacredin.testtasksociality.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import okhttp3.internal.notify
import okhttp3.internal.notifyAll
import ru.lacredin.testtasksociality.R
import ru.lacredin.testtasksociality.models.LocationsItem
import ru.lacredin.testtasksociality.ui.viewholders.LoadItemViewHolder
import ru.lacredin.testtasksociality.ui.viewholders.LocationViewHolder


class RecycleAdapter(
    val context: Context?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var listData: MutableList<Any> = mutableListOf()

    internal var showLoadItem = false

    @Synchronized
    fun setStatusLoadItem(showLoad: Boolean){
        showLoadItem = showLoad
        notifyAll()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v: RecyclerView.ViewHolder = if (DATA_ITEM == viewType) {
            LayoutInflater.from(context).inflate(R.layout.item_location, parent, false).let {
                LocationViewHolder(it)
            }
        } else {
            LayoutInflater.from(context).inflate(R.layout.item_load_elements, parent, false).let {
                LoadItemViewHolder(it)
            }
        }
        return v
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (!checkItemLoad(position))
            (holder as LocationViewHolder).bind(listData[position] as LocationsItem)
    }

    override fun getItemViewType(position: Int): Int {
        if (showLoadItem && position == getSize() - 1) {
            return LOAD_ITEM
        }
        return DATA_ITEM
    }

    override fun getItemCount(): Int {
        return getSize()
    }

    fun getSize(): Int {
        return listData.size + if (showLoadItem) 1 else 0
    }

    fun checkItemLoad(position: Int): Boolean {
        return showLoadItem && position == getSize() - 1
    }

    companion object {
        const val DATA_ITEM = 1
        const val LOAD_ITEM = 2
    }
}