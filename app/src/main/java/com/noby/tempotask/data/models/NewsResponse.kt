package com.noby.tempotask.data.models

/**
 * Created by Ahmed Noby Ahmed on 6/16/21.
 */
data class NewsResponse(val status: String, val totalResults: Int, val articles: List<Article>)