package com.nischal.newsapp.utils.networkUtils

import com.nischal.newsapp.R
import com.nischal.newsapp.utils.ResourceProvider
import retrofit2.HttpException
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Catch and operate on exceptions like
 * - [UnknownHostException],
 * - [SocketTimeoutException],
 * - [NoSuchElementException],
 * - [HttpException]
 */
fun <T> Exception.handleException(resourceProvider: ResourceProvider): DataResource<T> {
    Timber.e(this)
    return when (this) {
        is UnknownHostException -> DataResource.Error(message = resourceProvider.getString(R.string.err_no_internet_connection))
        is SocketTimeoutException -> DataResource.Error(message = resourceProvider.getString(R.string.err_no_internet_connection))
        is NoSuchElementException -> DataResource.Error(message = resourceProvider.getString(R.string.err_unknown_error))
        is HttpException -> {
            //Handle the error response body here, for now generic message is returned
            DataResource.Error(message = resourceProvider.getString(R.string.err_unknown_error))
        }
        else -> DataResource.Error(message = resourceProvider.getString(R.string.err_unknown_error))
    }
}