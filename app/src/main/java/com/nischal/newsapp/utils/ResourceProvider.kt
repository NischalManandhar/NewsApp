package com.nischal.newsapp.utils

import android.content.res.Resources
import javax.inject.Inject

interface ResourceProvider {
    fun getString(resourceId: Int): String
}

class ResourceProviderImpl
@Inject constructor(
    private val resources: Resources
) : ResourceProvider {
    override fun getString(resourceId: Int): String {
        return resources.getString(resourceId)
    }
}