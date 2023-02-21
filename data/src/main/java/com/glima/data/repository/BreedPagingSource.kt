package com.glima.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.glima.domain.model.Breed
import com.glima.domain.repository.BreedRepository
import retrofit2.HttpException
import java.io.IOException

class BreedPagingSource(private val repository: BreedRepository) : PagingSource<Int, Breed>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Breed> {
        return try {
            val result = repository.fetchBreedsByPage(params.key ?: INITIAL_PAGE_INDEX)

            LoadResult.Page(
                data = result,
                prevKey = params.key,
                nextKey = if (result.isEmpty()) {
                    null
                } else {
                    params.key?.plus(1) ?: INITIAL_PAGE_INDEX.plus(1)
                }
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Breed>): Int? = null

    companion object {
        private const val INITIAL_PAGE_INDEX = 0
    }
}
