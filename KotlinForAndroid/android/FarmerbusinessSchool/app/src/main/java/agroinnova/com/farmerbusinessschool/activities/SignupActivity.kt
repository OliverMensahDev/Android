package com.agroinnova.farmerbusinessschool.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import  com.agroinnova.farmerbusinessschool.R;
import com.agroinnova.farmerbusinessschool.data.DatabaseHelper
import com.agroinnova.farmerbusinessschool.models.FarmerModel
import com.agroinnova.farmerbusinessschool.utils.UIutils
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        UIutils.fadeIn(cardview)
        UIutils.unFocus(this);

        buttonRegister.setOnClickListener {
            val phone: String = inputEditTextPhone.text.toString().trim()
            val pincode: String = inputEditTextPinCode.text.toString().trim()
            val name: String = inputEditTextName.text.toString().trim()
            if (
                UIutils.validateInput(name, inputLayoutName, "Enter a Name") &&
                UIutils.validateInput(phone, inputLayoutPhone, "Enter a Phone Number") &&
                UIutils.isValidPhone(phone, inputLayoutPhone) &&
                UIutils.validateInput(pincode, inputLayoutPinCode, "Enter a Pin Code") &&
                UIutils.isValidPinCode(pincode, inputLayoutPinCode)
            ){
                signUp(name, phone, pincode);
            }
        }
    }

    private fun signUp(name:String, phone:String, pincode: String){
        val farmerModel = FarmerModel();
        farmerModel.name = name
        farmerModel.phoneNumber = phone
        farmerModel.pinCode = pincode
        val helper = DatabaseHelper.getInstance(this)
        if(helper.registerFarmer(farmerModel)){
            Toast.makeText(this, "Successfully Created a Farmer", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, LoginActivity::class.java))
        }else{
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
        }
    }
}
