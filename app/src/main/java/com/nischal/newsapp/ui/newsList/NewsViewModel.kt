package com.nischal.newsapp.ui.newsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nischal.newsapp.ui.newsList.models.NewsDisplayModel
import com.nischal.newsapp.utils.liveData.SingleLiveEvent
import com.nischal.newsapp.utils.networkUtils.DataResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel
@Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {
    private var _newsResult = MutableLiveData<List<NewsDisplayModel>?>()
    //Observe this for getting the news list
    val newsResult: LiveData<List<NewsDisplayModel>?>
        get() = _newsResult

    private val _isNewsRefreshing = MutableLiveData(false)
    //Observe this for updating the status of swipe refresh progress
    val isNewsRefreshing: LiveData<Boolean>
        get() = _isNewsRefreshing

    //Observe this for getting error from api call
    val errorMessage = SingleLiveEvent<String>()

    //Observe this for getting news item click event
    val newsItemClickEvent = SingleLiveEvent<NewsDisplayModel>()

    /**
     * Method to fetch news list from api.
     */
    fun fetchNews() {
        viewModelScope.launch {
            if (isNewsRefreshing.value == false) {
                _isNewsRefreshing.postValue(true)
                when (val result = newsRepository.fetchNews()) {
                    is DataResource.Error -> {
                        _isNewsRefreshing.postValue(false)
                        errorMessage.postValue(result.message)
                    }
                    is DataResource.Progress -> {
                    }
                    is DataResource.Success -> {
                        _newsResult.postValue(result.data)
                        _isNewsRefreshing.postValue(false)
                    }
                }
            }
        }
    }

    /**
     * Publish news item click event to observer.
     */
    fun onNewsItemClicked(newsDisplayModel: NewsDisplayModel) {
        newsItemClickEvent.postValue(newsDisplayModel)
    }
}