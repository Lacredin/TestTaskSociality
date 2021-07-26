package ru.lacredin.testtasksociality.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import okhttp3.internal.notifyAll
import ru.lacredin.testtasksociality.R
import ru.lacredin.testtasksociality.ui.items.BaseItem
import ru.lacredin.testtasksociality.ui.viewholders.UniversalViewHolder


class CustomRecycleAdapter(
    val context: Context?,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var listData: MutableList<BaseItem> = mutableListOf()

    internal var showLoadItem = false

    @Synchronized
    fun setStatusLoadItem(showLoad: Boolean) {
        showLoadItem = showLoad
        notifyAll()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(context).inflate(viewType, parent, false).let {
            UniversalViewHolder(it)
        }

        return v
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (!checkItemLoad(position))
            (holder as UniversalViewHolder).bind(listData[position])
    }

    override fun getItemViewType(position: Int): Int {
        if (showLoadItem && position == getSize() - 1) {
            return R.layout.item_load_elements
        }
        return listData[position].getLayoutId()
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
}