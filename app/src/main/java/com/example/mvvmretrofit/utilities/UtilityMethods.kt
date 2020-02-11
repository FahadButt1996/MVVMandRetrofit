package com.example.mvvmretrofit.utilities

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.mvvmretrofit.R

//Reachbility of Internet
fun checkNetworkConnectivity(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = cm.activeNetworkInfo
    return netInfo != null && netInfo.isConnectedOrConnecting && (netInfo.type == ConnectivityManager.TYPE_MOBILE || netInfo.type == ConnectivityManager.TYPE_WIFI)
}

var dialog: AlertDialog? = null
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun showProgressDialog(context: Context) {
    if (dialog == null) {
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false) // if you want user to wait for some process to finish,

        builder.setView(R.layout.progress_dialog)
        dialog = builder.create()
        dialog!!.setCanceledOnTouchOutside(false)
        dialog!!.setCancelable(false)
        dialog?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    if (context != null) {
        dialog!!.show()
    }
}

fun hideProgressDialog() {
    if (dialog != null) {
        dialog!!.dismiss()
        dialog = null
    }
}

fun Activity.showToast(message: String) {
    Toast.makeText(this , message, Toast.LENGTH_SHORT).show()
}