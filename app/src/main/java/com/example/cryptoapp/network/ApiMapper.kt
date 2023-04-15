package com.example.cryptoapp.network

import com.example.cryptoapp.data.db.Crypto

class ApiMapper {
    fun map(cryptoApiResponse: CryptoApiResponse) = Crypto(name = cryptoApiResponse.name,
            symbol = cryptoApiResponse.symbol, price = cryptoApiResponse.current_price,
            image = cryptoApiResponse.image, dailyChange = cryptoApiResponse.price_change_24h,
            dailyChangePercentage = cryptoApiResponse.price_change_percentage_24h,
            high = cryptoApiResponse.high_24h, low = cryptoApiResponse.low_24h,
            volume = cryptoApiResponse.total_volume, supply = cryptoApiResponse.total_supply,
            marketCap = cryptoApiResponse.market_cap,
            chartData = cryptoApiResponse.sparkline_in_7d.price)
}