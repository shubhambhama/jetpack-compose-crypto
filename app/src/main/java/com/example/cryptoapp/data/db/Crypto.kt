package com.example.cryptoapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_table")
data class Crypto(@PrimaryKey val symbol: String, val price: Double, val name: String,
                  val image: String, val dailyChange: Double,
                  val dailyChangePercentage: Double, val high: Double, val low: Double,
                  val volume: Double,
                  val supply: Double?,
                  val marketCap: Long,
                  val chartData: List<Float>) : java.io.Serializable {
    override fun equals(other: Any?): Boolean {
        return (other as Crypto).symbol == symbol
    }
}