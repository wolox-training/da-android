package ar.com.wolox.android.example.utils

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

fun View.visibility(flag: Boolean?) {
    when (flag) {
        true -> this.visibility = VISIBLE
        else -> this.visibility = GONE
    }
}