package com.mvvm.examplearchitecture.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity


open class ProgressBar(): AppCompatActivity(){

    open fun Context.progressBarShow(context: Context) {

        /*val dialogBuilder = AlertDialog.Builder(context, R.style.Theme_Dialog)
        val dialogView =
        dialogBuilder.setView(dialogView)
        dialogBuilder.setPositiveButton(getString(R.string.ok_text), object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface, id: Int) {
                when (radioGroupChat.checkedRadioButtonId) {
                    R.id.radioButton_user_chat -> {
                        (activity as HomeActivity).replaceFragment(MySkippersFragment.getInstance(isFromChat = true))
                    }
                    R.id.radioButton_circle_chat -> {
                        (activity as HomeActivity).replaceFragment(PickCircleFragment.getInstance(
                            PickCircleFragment.NEW_CIRCLE_CHAT), true)
                    }
                }
            }
        })
        dialogBuilder.setNegativeButton(getString(R.string.cancel_text), object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
            }
        })

        val alertDialog = dialogBuilder.create()
        alertDialog.show()*/





        /*val dialog = Dialog(this, R.style.Theme_Dialog)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_progressbar)

        dialog.show()*/

    }
}