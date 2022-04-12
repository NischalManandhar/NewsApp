package com.nischal.newsapp.di

import com.nischal.newsapp.BuildConfig
import com.nischal.newsapp.data.api.ApiService
import com.nischal.newsapp.utils.networkUtils.TLSSocketFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(
        tlsSocketFactory: TLSSocketFactory,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .sslSocketFactory(tlsSocketFactory, tlsSocketFactory.trustManager)
            .connectTimeout(NetworkConstants.CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(NetworkConstants.READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(NetworkConstants.WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideTlsSocketFactory(): TLSSocketFactory = TLSSocketFactory()

    @Provides
    @Singleton
    fun provideRetrofitService(okHttpClient: OkHttpClient): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }
}

object NetworkConstants {
    const val CONNECTION_TIMEOUT = 20
    const val READ_TIMEOUT = 60
    const val WRITE_TIMEOUT = 60
}