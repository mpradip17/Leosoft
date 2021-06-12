package com.kotlintest.app.utility

import android.content.res.Resources
import android.util.TypedValue
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.kotlintest.app.view.adapter.ImageAdapter
import java.util.*

object CustomBinding {

    fun dp2px(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            Resources.getSystem().displayMetrics
        ).toInt()
    }


    @BindingAdapter("load_allLead")
    @JvmStatic
    fun loadUsers(recyclerView: RecyclerView, adapter: Any?) {
        when (adapter) {


            is ImageAdapter -> {
                (Objects.requireNonNull<RecyclerView.ItemAnimator>(recyclerView.getItemAnimator()) as SimpleItemAnimator).supportsChangeAnimations =
                    false
                recyclerView.adapter = adapter as RecyclerView.Adapter<*>?
            }


        }
    }

}