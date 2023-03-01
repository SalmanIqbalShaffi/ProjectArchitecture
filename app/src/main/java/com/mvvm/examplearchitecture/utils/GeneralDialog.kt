package com.example.agentmate.utils.extensions

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.agentmate.R
import org.w3c.dom.Text
import java.io.IOException
import kotlin.jvm.Throws

open class GeneralDialog {
    open fun showGeneralDialog(
        context: Context,
        setCancelAble: Boolean,
        isCancel: Boolean,
        getPair: OnclickedOK?,
        yourTitle: String,
        newImageView: Int,
        yourMessage: String,
        isOtherOK: Boolean,
        getOk: OnclickedOK?
    ) {
        val dialog = Dialog(context, R.style.Theme_Dialog)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(setCancelAble)
        dialog.setContentView(R.layout.general_dialog_box)

        val okBtn = dialog.findViewById(R.id.btnNext) as? TextView
        val cancelBtn = dialog.findViewById(R.id.btnCancel) as? TextView
        val okSingleBtn = dialog.findViewById(R.id.okSingleButton) as? TextView
        val imageView = dialog.findViewById(R.id.imageView) as? ImageView
        val pairButton = dialog.findViewById(R.id.pairButtons) as? ConstraintLayout

        val title = dialog.findViewById(R.id.tvTitle) as TextView
        val message = dialog.findViewById(R.id.tvMessage) as TextView

        // for single and pair button.
        if (isCancel) {
            pairButton?.isVisible = true
            okSingleBtn?.isVisible = false
        } else {
            pairButton?.isVisible = false
            okSingleBtn?.isVisible = true
        }
        // for image and title
        if (yourTitle.isEmpty()){
            imageView?.isVisible = true
            title.isVisible = false
            imageView?.setImageDrawable(ContextCompat.getDrawable(context, newImageView))
        }else{
            imageView?.isVisible = false
            title.isVisible = true
            title.text = yourTitle
        }

        message.text = yourMessage

        // for pair button
        if (isCancel){
            okBtn?.setOnClickListener {
                getPair?.getOkValue(true)
                dialog.dismiss()
            }

        }else{
            okBtn?.setOnClickListener {
                dialog.dismiss()
            }
        }

        cancelBtn?.setOnClickListener {
            dialog.dismiss()
        }

        // for single button
        if (isOtherOK){
            okSingleBtn?.setOnClickListener {
                getOk?.getOkValue(true)
                dialog.dismiss()
            }
        }else{
            okSingleBtn?.setOnClickListener {
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    interface OnclickedOK{
        @Throws(IOException::class)
        fun getOkValue(fromOK: Boolean)
    }
}