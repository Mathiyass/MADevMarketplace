package com.madev.marketplace.util

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat
import java.util.Locale

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun View.showSnackbar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, message, duration).show()
}

fun View.visible() { visibility = View.VISIBLE }
fun View.gone() { visibility = View.GONE }
fun View.invisible() { visibility = View.INVISIBLE }

fun Double.formatPrice(): String {
    val format = NumberFormat.getCurrencyInstance(Locale.US)
    return format.format(this)
}

fun String.truncateUuid(): String {
    return if (length > 8) "${substring(0, 8)}..." else this
}

fun Double.formatFileSize(): String {
    return when {
        this >= 1000 -> String.format("%.1f GB", this / 1000.0)
        this >= 1 -> String.format("%.0f MB", this)
        else -> String.format("%.0f KB", this * 1000)
    }
}
