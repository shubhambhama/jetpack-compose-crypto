package com.example.cryptoapp.data.repo

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.cryptoapp.data.db.Crypto
import com.example.cryptoapp.data.db.Dao
import com.example.cryptoapp.network.ApiMapper
import com.example.cryptoapp.network.CryptoApi

class CryptoRepoImplementation(private val cryptoApi: CryptoApi,
                               private val dao: Dao,
                               private val apiMapper: ApiMapper) : CryptoRepository {

    @WorkerThread
    override suspend fun getCryptoPages(page: Int, pageSize: Int): List<Crypto> {
        val response = cryptoApi.getAllCrypto(page)
        return if (response.isSuccessful && !response.body().isNullOrEmpty()) {
            val cryptoApiResponseList = response.body()
            val cryptoList = cryptoApiResponseList?.map { cryptoApiResponse ->
                apiMapper.map(cryptoApiResponse)
            }
            cryptoList?: emptyList()
        } else {
            emptyList()
        }
    }

    override suspend fun getCryptoData(): LiveData<List<Crypto>> = dao.getCryptos()
}