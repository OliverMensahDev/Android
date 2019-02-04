package com.agroinnova.farmerbusinessschool.utils

import android.app.Activity
import android.support.design.widget.TextInputLayout
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.view.animation.AlphaAnimation

object UIutils {
    fun validateInput(input: String, inputEditText: TextInputLayout, errorMessage: String): Boolean {
        if (input.isEmpty()) {
            inputEditText.error = errorMessage
            return false
        } else {
            inputEditText.isErrorEnabled = false
            return true
        }
    }

    fun isValidPhone(phone: String, inputEditText: TextInputLayout): Boolean {
        if (!Patterns.PHONE.matcher(phone.trim()).matches()) {
            inputEditText.error ="Enter Appropriate Phone Number"
            return false;
        }else if(phone.trim().length != 10) {
            inputEditText.error = "Phone Number Must be 10 Characters"
            return false
        }
        else {
            inputEditText.isErrorEnabled = false
            return true
        }
    }

    fun isValidPinCode(pincode: String, inputEditText: TextInputLayout): Boolean {
        if (!Patterns.PHONE.matcher(pincode.trim()).matches()) {
            inputEditText.error = "Enter Numeric Characters"
            return false
        }else if(pincode.trim().length != 4) {
            inputEditText.error = "Pin Code Must be 4 Characters"
            return false
        } else {
            inputEditText.isErrorEnabled = false
            return true
        }
    }

    fun fadeIn(view: View) {
        val alphaAnimation = AlphaAnimation(0f, 1f)
        alphaAnimation.fillBefore = true
        alphaAnimation.duration = 3000
        view.startAnimation(alphaAnimation)
    }

    fun unFocus(myActivityReference: Activity){
        myActivityReference.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

}