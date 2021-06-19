package com.noby.tempotask.ui.NewsFragment

import androidx.lifecycle.*
import com.akshay.newsapp.news.domain.NewsRepository
import com.noby.tempotask.data.models.NewsResponse
import com.noby.tempotask.data.network.Resource
import com.noby.tempotask.data.room.entity.NewsArticleDb
import com.noby.tempotask.util.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import retrofit2.Response
import javax.inject.Inject


/**
 * Created by Ahmed Noby Ahmed on 6/16/21.
 */

    @HiltViewModel
    class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
) : ViewModel(){

    val searchQuery =  MutableStateFlow("")
     private val newsflow =  searchQuery.flatMapLatest {
         newsRepository.getNewsArticles(it)
     }
        fun getNewsArticles(): LiveData<ViewState<List<NewsArticleDb>>> = newsflow.asLiveData()
}