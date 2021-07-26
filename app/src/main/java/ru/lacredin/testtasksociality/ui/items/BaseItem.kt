package ru.lacredin.testtasksociality.ui.items

import android.view.View
import androidx.annotation.LayoutRes

abstract class BaseItem(

) {
    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun bind(viewItem: View)
}