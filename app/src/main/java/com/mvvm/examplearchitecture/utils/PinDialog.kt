package com.example.agentmate.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.widget.TextView
import com.chaos.view.PinView
import com.example.agentmate.R
import com.example.agentmate.utils.extensions.GeneralDialog
import java.io.IOException
import kotlin.jvm.Throws

object PinDialog : GeneralDialog(){

    fun Context.showPinDialog(context: Context, pinEntered: OnPinEntered) {
        val dialog = Dialog(this, R.style.Theme_Dialog)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.mpin_dialog)

        val btnOk = dialog.findViewById(R.id.btnNext) as? TextView
        val btnCancel = dialog.findViewById(R.id.btnCancel) as? TextView
        val pin = dialog.findViewById(R.id.firstPinView) as? PinView

        btnOk?.setSafeOnClickListener {
            if (pin?.text?.isEmpty() == true) {
//                Toast.makeText(context, "plz enter valid pin", Toast.LENGTH_SHORT).show()
                showGeneralDialog(
                    context = context,
                    setCancelAble = false,
                    isCancel = false,
                    getPair = null,
                    yourTitle = "Please Enter Pin",
                    newImageView = 0,
                    yourMessage = "Can't Empty This Field",
                    isOtherOK = false,
                    getOk = null
                )
            }else if (pin?.text?.length!! < 4){
                showGeneralDialog(
                    context = context,
                    setCancelAble = false,
                    isCancel = false,
                    getPair = null,
                    yourTitle = "Please Enter Pin",
                    newImageView = 0,
                    yourMessage = "Please Enter 4 Digit Pin",
                    isOtherOK = false,
                    getOk = null
                )
            }
            else{
                pinEntered.pinEntered(pin.text.toString())
                dialog.dismiss()
            }
        }
        btnCancel?.setSafeOnClickListener {
//            negativeButtonFunction?.invoke()
            dialog.dismiss()
            pinEntered.pinEntered("cancel")
        }

        dialog.show()
    }

    interface OnPinEntered {
        @Throws(IOException::class)
        fun pinEntered(pin: String)
    }

    fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
        val safeClickListener = SafeClickListener {
            onSafeClick(it)
        }
        setOnClickListener(safeClickListener)
    }

}