package com.example.kpproject.database.roomdatabase

import androidx.room.Database
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kpproject.database.dao.MoneyManagementDao
import com.example.kpproject.database.dao.TimeManagementDao
import com.example.kpproject.database.entity.MoneyManagement
import com.example.kpproject.database.entity.TimeManagement

@Database(entities = [MoneyManagement::class, TimeManagement::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moneyManagementDao(): MoneyManagementDao
    abstract fun timeManagementDao(): TimeManagementDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}