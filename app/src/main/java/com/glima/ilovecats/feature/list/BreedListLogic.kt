package com.glima.ilovecats.feature.list

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.glima.data.repository.BreedPagingSource
import com.glima.domain.model.Breed
import kotlinx.coroutines.flow.Flow

class BreedListLogic(private val breedPagingSource: BreedPagingSource) {

    fun loadNext(): Flow<PagingData<Breed>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { breedPagingSource }
        ).flow
    }
}