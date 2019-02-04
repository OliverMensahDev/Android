package com.agroinnova.farmerbusinessschool.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.agroinnova.farmerbusinessschool.models.FarmerModel
import com.agroinnova.farmerbusinessschool.models.MoneyInItemModel
import com.agroinnova.farmerbusinessschool.models.MoneyInModel
import com.agroinnova.farmerbusinessschool.models.Users
import com.agroinnova.farmerbusinessschool.utils.Constants
import java.lang.Exception

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASENAME, null, DATABASEVERSION) {

    private var CREATE_TABLE_FARMERS =
        ("CREATE TABLE " + Constants.TBL_FARMER + "("
            + Constants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Constants.PHONENUMBER + " TEXT  UNIQUE, "
            + Constants.PINCODE + " TEXT, "
            + Constants.FARMERNAME + " TEXT);")

    private var CREATE_TABLE_MONEY_IN_ITEM =
        ("CREATE TABLE " + Constants.TBL_MONEY_IN_ITEMS + "("
                + Constants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Constants.ITEMNAME + " TEXT  UNIQUE );")


    private var CREATE_TABLE_NEW_MONEY_IN =
        ("CREATE TABLE " + Constants.TBL_NEW_MONEY_IN + "("
                + Constants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Constants.USER + " TEXT, "
                + Constants.QUANTITY + " TEXT, "
                + Constants.UNITPRICE + " TEXT, "
                + Constants.TOTALPRICE + " TEXT, "
                + Constants.DATEADDED + " TEXT, "
                + Constants.ITEMNAME + " TEXT);")

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(CREATE_TABLE_FARMERS)
        db.execSQL(CREATE_TABLE_MONEY_IN_ITEM)
        db.execSQL(CREATE_TABLE_NEW_MONEY_IN)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + Constants.TBL_FARMER)
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TBL_MONEY_IN_ITEMS)
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TBL_NEW_MONEY_IN)
        onCreate(db)
    }

    companion object {
        private val DATABASENAME = Constants.DATABASENAME
        private val DATABASEVERSION = Constants.DATABASEVERSION

        @Volatile private var INSTANCE: DatabaseHelper? = null
        fun getInstance(context: Context): DatabaseHelper {
            return INSTANCE ?: synchronized(this){
                INSTANCE?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }
        }
        private fun buildDatabase(context: Context): DatabaseHelper{
            return DatabaseHelper(context)

        }
    }

    fun registerFarmer(farmerModel: FarmerModel): Boolean{
        return try {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(Constants.FARMERNAME, farmerModel.name)
            values.put(Constants.PHONENUMBER, farmerModel.phoneNumber)
            values.put(Constants.PINCODE, farmerModel.pinCode)
            return db.insert(Constants.TBL_FARMER, null, values) > -1
        }catch (e: Exception){
            false
        }
    }
    fun getFarmers(): List<FarmerModel> {
        val db = this.writableDatabase
        val list = ArrayList<FarmerModel>()
        val cursor: Cursor
        cursor = db.rawQuery("SELECT * FROM ${Constants.TBL_FARMER}", null)
        if (cursor != null) {
            if (cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    val farmerModel = FarmerModel()
                    farmerModel.name = cursor.getString(cursor.getColumnIndex(Constants.FARMERNAME))
                    farmerModel.pinCode = cursor.getString(cursor.getColumnIndex(Constants.PINCODE))
                    farmerModel.phoneNumber = cursor.getString(cursor.getColumnIndex(Constants.PHONENUMBER))
                    list.add(farmerModel)
                } while (cursor.moveToNext())
            }
        }
        return list
    }

    fun UpdateFarmer(users: Users) {
        val db = this.writableDatabase
        var values = ContentValues()
        values.put("userID", users.userID)
        values.put("userName", users.userName)
        values.put("userAge", users.userAge)
        val retVal = db.update("USER", values, "userID = " + users.userID, null)
        if (retVal >= 1) {
            Log.v("@@@WWe", " Record updated")
        } else {
            Log.v("@@@WWe", " Not updated")
        }
        db.close()
    }

    fun getFarmer(phone: String): FarmerModel {
        val db = this.readableDatabase
        val selectQuery = "SELECT  * FROM ${Constants.TBL_FARMER} WHERE  ${Constants.PHONENUMBER } = '$phone';"
        val cursor = db.rawQuery(selectQuery, null)
        val farmerModel = FarmerModel()
        if (cursor != null) {
            if (cursor.count == 1) {
                cursor.moveToFirst()
                farmerModel.name = cursor.getString(cursor.getColumnIndex(Constants.FARMERNAME))
                farmerModel.pinCode = cursor.getString(cursor.getColumnIndex(Constants.PINCODE))
                farmerModel.phoneNumber = cursor.getString(cursor.getColumnIndex(Constants.PHONENUMBER))
            }
        }
        cursor.close()
        db.close()
        return farmerModel
    }


    fun createMoneyInItem(moneyInItemModel: MoneyInItemModel): Boolean{
        return try {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(Constants.ITEMNAME, moneyInItemModel.name)
            return db.insert(Constants.TBL_MONEY_IN_ITEMS, null, values) > -1
        }catch (e: Exception){
            false
        }
    }

    fun createNewMoneyIn(moneyInModel: MoneyInModel, farmerNumber: String): Boolean{
        return try {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(Constants.ITEMNAME, moneyInModel.itemName)
            values.put(Constants.QUANTITY, moneyInModel.quantity)
            values.put(Constants.UNITPRICE, moneyInModel.unitPrice)
            values.put(Constants.TOTALPRICE, moneyInModel.totalPrice)
            values.put(Constants.DATEADDED, moneyInModel.dateAdded)
            values.put(Constants.USER, farmerNumber)
            return db.insert(Constants.TBL_NEW_MONEY_IN, null, values) > -1
        }catch (e: Exception){
            false
        }
    }

    fun getMoneyIns(): List<MoneyInModel> {
        val db = this.writableDatabase
        val list = ArrayList<MoneyInModel>()
        val cursor: Cursor
        cursor = db.rawQuery("SELECT * FROM ${Constants.TBL_NEW_MONEY_IN}", null)
        if (cursor != null) {
            if (cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    val moneyInModel = MoneyInModel()
                    moneyInModel.itemName = cursor.getString(cursor.getColumnIndex(Constants.ITEMNAME))
                    moneyInModel.quantity = cursor.getDouble(cursor.getColumnIndex(Constants.QUANTITY))
                    moneyInModel.unitPrice = cursor.getDouble(cursor.getColumnIndex(Constants.UNITPRICE))
                    moneyInModel.totalPrice = cursor.getDouble(cursor.getColumnIndex(Constants.TOTALPRICE))
                    moneyInModel.dateAdded = cursor.getString(cursor.getColumnIndex(Constants.DATEADDED))
                    list.add(moneyInModel)
                } while (cursor.moveToNext())
            }
        }
        return list
    }

    fun getMoneyInItems(): List<MoneyInItemModel> {
        val db = this.writableDatabase
        val list = ArrayList<MoneyInItemModel>()
        val cursor: Cursor
        cursor = db.rawQuery("SELECT * FROM ${Constants.TBL_MONEY_IN_ITEMS}", null)
        if (cursor != null) {
            if (cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    val moneyInItem = MoneyInItemModel()
                    moneyInItem.name = cursor.getString(cursor.getColumnIndex(Constants.ITEMNAME))
                    moneyInItem.id = cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID))
                    list.add(moneyInItem)
                } while (cursor.moveToNext())
            }
        }
        return list
    }

    fun getMoneyInItemList(): List<String> {
        val db = this.writableDatabase
        val list = ArrayList<String>()
        val cursor: Cursor
        cursor = db.rawQuery("SELECT * FROM ${Constants.TBL_MONEY_IN_ITEMS}", null)
        if (cursor != null) {
            if (cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    list.add(cursor.getString(cursor.getColumnIndex(Constants.ITEMNAME)))
                } while (cursor.moveToNext())
            }
        }
        return list
    }
    fun DeleteFarmer(users: Users) {
        val db = this.writableDatabase
        var values = ContentValues()
        values.put("userID", users.userID)
        values.put("userName", users.userName)
        values.put("userAge", users.userAge)
        val retVal = db.delete("USER", "userID = " + users.userID, null)
        if (retVal >= 1) {
            Log.v("@@@WWe", " Record deleted")
        } else {
            Log.v("@@@WWe", " Not deleted")
        }
        db.close()
    }
}