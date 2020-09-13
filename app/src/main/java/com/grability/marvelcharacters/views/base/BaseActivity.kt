package com.grability.marvelcharacters.views.base

import android.app.Activity
import android.app.ProgressDialog
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {

    lateinit var progressDialog: ProgressDialog

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showProgressDialog(title: String?, message: String, indeterminate: Boolean, cancelable: Boolean){
        progressDialog = ProgressDialog.show(this@BaseActivity, title, message, indeterminate, cancelable)
    }

    fun hideProgressDialog() {
        if(progressDialog.isShowing)
            progressDialog.dismiss()
    }
}