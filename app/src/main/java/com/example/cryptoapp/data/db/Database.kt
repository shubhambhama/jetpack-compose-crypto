package com.example.cryptoapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Crypto::class], version = 1, exportSchema = false)
@TypeConverters(ListTypeConverter::class)
abstract class CryptoDatabase : RoomDatabase() {
    abstract fun cryptoDao(): Dao

    companion object {
        private var instance: CryptoDatabase? = null
        private var LOCK = Any()

        fun getInstance(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context.applicationContext).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
                context.applicationContext,
                CryptoDatabase::class.java, "crypto_db").build()
    }
}