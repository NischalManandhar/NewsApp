package com.nischal.newsapp.data.api

import com.nischal.newsapp.ui.newsList.models.NewsResponse
import retrofit2.http.GET

interface ApiService {
    @GET(UrlContract.EndPoints.NEWS)
    suspend fun getNewsAsync(): NewsResponse
}
