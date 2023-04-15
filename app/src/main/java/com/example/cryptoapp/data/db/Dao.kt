package com.example.cryptoapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.TypeConverters

@androidx.room.Dao
@TypeConverters
interface Dao {

    @Transaction
    @Query("select * from crypto_table")
    fun getCryptos(): LiveData<List<Crypto>>
}