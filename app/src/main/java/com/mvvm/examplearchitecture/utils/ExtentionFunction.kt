package com.example.agentmate.utils

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.text.Editable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.mvvm.examplearchitecture.BuildConfig
import com.mvvm.examplearchitecture.R

//@RequiresApi(Build.VERSION_CODES.M)
fun Fragment.setStatusBarColor(colorId: Int = R.color.white) {

    activity!!.window.statusBarColor = activity!!.getColor(colorId)
}


fun Context.isNetworkConnected(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
}

fun Context.toast(context: Context, string: String) {
    if (BuildConfig.DEBUG) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
    }
}

fun Context.recyclerviewHandle(cardView: CardView, imageUp: ImageView, imageDown: ImageView) {

    if (cardView.isVisible) {
        cardView.isVisible = false
        imageDown.isVisible = true
        imageUp.isVisible = false

    } else {
        cardView.isVisible = true
        imageDown.isVisible = false
        imageUp.isVisible = true
    }
}

fun String.setOnEditText(): Editable = Editable.Factory.getInstance().newEditable(this)

