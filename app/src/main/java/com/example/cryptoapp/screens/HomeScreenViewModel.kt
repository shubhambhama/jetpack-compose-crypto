package com.example.cryptoapp.screens

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.cryptoapp.data.repo.CryptoRepository
import com.example.cryptoapp.network.Network
import com.example.cryptoapp.util.PageNumSource
import kotlinx.coroutines.Dispatchers

class HomeScreenViewModel(context: Context) : ViewModel() {
    private val cryptoRepository: CryptoRepository = Network.createRepository(context)

    fun getAllCryptos(pageSize: Int = 20) = Pager(
            config = PagingConfig(pageSize = pageSize, initialLoadSize = pageSize)) {
        PageNumSource { pageNum, pageSize ->
            cryptoRepository.getCryptoPages(pageNum, pageSize)
        }
    }.flow.cachedIn(viewModelScope)

    val cryptoLiveData = liveData(Dispatchers.IO) {
        emitSource(cryptoRepository.getCryptoData())
    }
}

class HomeScreenViewModelFactory(val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeScreenViewModel(context) as T
    }
}