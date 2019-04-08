package ar.com.wolox.training.example.utils

import android.content.Context
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast

fun View.visibility(flag: Boolean?) {
    when (flag) {
        true -> this.visibility = VISIBLE
        else -> this.visibility = GONE
    }
}

fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()