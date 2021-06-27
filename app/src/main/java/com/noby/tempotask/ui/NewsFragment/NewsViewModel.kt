package com.noby.tempotask.ui.NewsFragment

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.akshay.newsapp.news.domain.NewsRepository
import com.noby.tempotask.data.models.NewsResponse
import com.noby.tempotask.data.network.Resource
import com.noby.tempotask.data.room.entity.NewsArticleDb
import com.noby.tempotask.util.Utils.isConnectingToInternet
import com.noby.tempotask.util.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


/**
 * Created by Ahmed Noby Ahmed on 6/16/21.
 */
    @HiltViewModel
    class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val app : Application
) : ViewModel(){
    private val  _getNewsArticles  =  MutableLiveData<ViewState<List<NewsArticleDb>>>()
    val  getNewsArticles: LiveData<ViewState<List<NewsArticleDb>>> get()  =  _getNewsArticles
       fun getNewsList(){
           viewModelScope.launch {
                newsRepository.getNewsArticles(app).collect {
                    _getNewsArticles.value =  it
               }
           }
       }
    fun SearchlistNewsList(searchquery:String){
        viewModelScope.launch {
            newsRepository.getNewsArticles(searchquery).collect {
                _getNewsArticles.value =  it
            }
        }
    }
}
