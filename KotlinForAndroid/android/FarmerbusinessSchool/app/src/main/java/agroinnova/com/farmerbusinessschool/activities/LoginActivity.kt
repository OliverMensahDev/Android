package com.agroinnova.farmerbusinessschool.activities
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.agroinnova.farmerbusinessschool.R
import com.agroinnova.farmerbusinessschool.data.DatabaseHelper
import com.agroinnova.farmerbusinessschool.models.FarmerModel
import com.agroinnova.farmerbusinessschool.models.MoneyInItemModel
import com.agroinnova.farmerbusinessschool.utils.UIutils
import kotlinx.android.synthetic.main.activity_login.*

/**
 * LoginActivity
 *  A view that presents the login screen
 *  A user can login or visit the registration page
 */
class LoginActivity : AppCompatActivity() {
   private lateinit var helper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login);
        helper = DatabaseHelper.getInstance(this)
//        printUser(helper.getMoneyInItems())

        UIutils.fadeIn(cardview)
        UIutils.unFocus(this);
        buttonLogin.setOnClickListener {
            val phone: String = inputEditTextPhone.text.toString().trim()
            val pincode: String = inputEditTextPinCode.text.toString().trim()
            if (
                UIutils.validateInput(phone, inputLayoutPhone, "Enter a Phone Number") &&
                UIutils.isValidPhone(phone, inputLayoutPhone) &&
                UIutils.validateInput(pincode, inputLayoutPinCode, "Enter a Pin Code") &&
                UIutils.isValidPinCode(pincode, inputLayoutPinCode)
            ) {
                if(login(phone,  pincode)){
                    startActivity(Intent(this, DashboardActivity::class.java));
                }else{
                    Toast.makeText(this, "User does not exit ", Toast.LENGTH_SHORT).show()
                }
            }
        }
        buttonRegister.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java));
        }
    }

    /**
     *  Login  logs using his/her phone number together with  a pincode
     *  @param phone
     *  @param pincode
     */
    fun login(phone: String, pincode: String):Boolean{
        val farmerModel: FarmerModel = helper.getFarmer(phone)
        Log.i("Farmer Details ", farmerModel.phoneNumber + " "+ farmerModel.pinCode);
        return farmerModel.phoneNumber == phone && farmerModel.pinCode == pincode
    }
    fun printUser(users: List<MoneyInItemModel>) {
        for (user: MoneyInItemModel in users) {
            Log.v("DATA", " Name : " + user.name + " id : " + user.id)
        }
    }

}
