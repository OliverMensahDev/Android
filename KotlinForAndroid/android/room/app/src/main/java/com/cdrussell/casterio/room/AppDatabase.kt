package com.cdrussell.casterio.room

import android.arch.persistence.room.RoomDatabase

abstract class AppDatabase: RoomDatabase(){
    companion object {
        @Volatile private var INSTANCE: AppDatabase?  = null
        fun getInstance
    }
}