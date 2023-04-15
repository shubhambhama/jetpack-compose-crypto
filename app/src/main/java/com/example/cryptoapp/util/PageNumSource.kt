package com.example.cryptoapp.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.EOFException
import java.io.IOException

class PageNumSource<Value : Any>(
        private val loadPage: suspend (pageNum: Int, pageSize: Int) -> List<Value>) :
        PagingSource<Int, Value>() {


    override fun getRefreshKey(state: PagingState<Int, Value>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> {
        return try {
            val page = params.key ?: 1
            val result = loadPage(page, params.loadSize) ?: return LoadResult.Error(
                    EOFException("no data"))
            LoadResult.Page(data = result, prevKey = if (page == 1) null else page - 1,
                    nextKey = page.plus(1))
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}