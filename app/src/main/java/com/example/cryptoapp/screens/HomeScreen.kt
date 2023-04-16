package com.example.cryptoapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.example.cryptoapp.InteractionsEvent
import com.example.cryptoapp.components.CryptoListItem
import com.example.cryptoapp.data.db.Crypto
import com.example.cryptoapp.util.CryptoLoadingView

@Composable
fun HomeScreen(interactionsEvent: (InteractionsEvent) -> Unit = {}) {
    val viewModel: HomeScreenViewModel = viewModel(
            factory = HomeScreenViewModelFactory(LocalContext.current)
    )
    val listScrollState = rememberLazyListState()
    val pagingItems = viewModel.getAllCryptos().collectAsLazyPagingItems()
    val cryptos = viewModel.cryptoLiveData.observeAsState(emptyList())
    Scaffold { paddingValue ->
        Column(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)) {
            Header()
            CryptoList(pagingItems = pagingItems, cryptos = cryptos.value,
                    listScrollState = listScrollState, interactionsEvent)
        }
    }
}

@Composable
fun Header() {
    Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentHeight(), Arrangement.Center) {
        Text(text = "Crypto App", style = MaterialTheme.typography.h3, fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun CryptoList(pagingItems: LazyPagingItems<Crypto>,
               cryptos: List<Crypto>,
               listScrollState: LazyListState,
               interactionsEvent: (InteractionsEvent) -> Unit) {
    val context = LocalContext.current
    LazyColumn(state = listScrollState) {
        itemsIndexed(pagingItems) { _, crypto ->
            crypto?.let {
                val isFav = cryptos.contains(crypto)
                CryptoListItem(crypto, isFav, interactionsEvent)
            }
        }
        pagingItems.apply {
            when {
                (loadState.refresh is LoadState.Loading) ||
                        (loadState.append is LoadState.Loading) -> {
                    item {
                        CryptoLoadingView(context = context)
                    }
                }
            }
        }
    }
}
