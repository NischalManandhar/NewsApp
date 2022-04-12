/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nischal.newsapp.utils.networkUtils

/**
 * A generic class that contains data of type [T]
 * Success : Api call success / finished
 * Progress : Api call in progress
 * Error : Api call failure with error
 */
sealed class DataResource<out T> {

    data class Success<T>(var data: T? = null) :
        DataResource<T>()

    data class Progress<T>(var data: T? = null, var message: String? = null) :
        DataResource<T>()

    data class Error<T>(var data: T? = null, val code: Int? = null, var message: String) :
        DataResource<T>()
}

