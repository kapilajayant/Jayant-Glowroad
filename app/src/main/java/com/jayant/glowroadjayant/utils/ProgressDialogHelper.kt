package com.jayant.glowroadjayant.utils

import android.app.ProgressDialog
import android.content.Context

class ProgressDialogHelper(context: Context){

    var mProgressDialog: ProgressDialog? = null

    init {

        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(context)
            mProgressDialog?.setMessage("Loading...")
            mProgressDialog?.isIndeterminate = true
        }
    }

    fun showProgressDialog() {
        mProgressDialog?.show()
    }

    fun hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog?.isShowing == true) {
            mProgressDialog?.dismiss()
        }
    }

}