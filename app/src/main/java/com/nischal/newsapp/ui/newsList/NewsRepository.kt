package com.nischal.newsapp.ui.newsList

import com.nischal.newsapp.data.api.ApiService
import com.nischal.newsapp.di.IODispatcher
import com.nischal.newsapp.ui.newsList.models.NewsDisplayModel
import com.nischal.newsapp.utils.ResourceProvider
import com.nischal.newsapp.utils.networkUtils.DataResource
import com.nischal.newsapp.utils.networkUtils.handleException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface NewsRepository {
    suspend fun fetchNews(): DataResource<List<NewsDisplayModel>>
}

class NewsRepositoryImpl
@Inject constructor(
    private val apiService: ApiService,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val resourceProvider: ResourceProvider
) : NewsRepository {
    override suspend fun fetchNews(): DataResource<List<NewsDisplayModel>> {
        return withContext(ioDispatcher) {
            try {
                val newsResult = apiService.getNewsAsync()
                // Prepare news list to be displayed
                val newsDisplayModels: List<NewsDisplayModel>? =
                    newsResult.assets?.map { assetItem ->
                        assetItem.getNewsDisplayModel()
                    }
                // Sort the news in descending order by timestamp
                val sortedNewsList = newsDisplayModels?.sortedByDescending {
                    it.timeStamp
                }
                DataResource.Success(sortedNewsList)
            } catch (e: Exception) {
                e.handleException(resourceProvider)
            }
        }
    }
}