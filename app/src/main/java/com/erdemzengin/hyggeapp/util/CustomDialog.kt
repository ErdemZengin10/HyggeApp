package com.erdemzengin.hyggeapp.util

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.erdemzengin.hyggeapp.R
import kotlinx.android.synthetic.main.custom_dialog.*

class CustomDialog(private val onClickInterface: OnClickInterface) : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.main_activity_viewpager_background)
        return inflater.inflate(R.layout.custom_dialog, container, false)

    }

    override fun onStart() {
        super.onStart()

        dialog!!.window?.setLayout(244, ViewGroup.LayoutParams.WRAP_CONTENT)
        btnGoToMainAcc.setOnClickListener {
            onClickInterface.onClickConfirm(dialog!!)
        }


    }
}

interface OnClickInterface {
    fun onClickConfirm(dialog: Dialog)

}