package com.noby.tempotask.api

import com.noby.tempotask.data.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ahmed Noby Ahmed on 6/16/21.
 */
interface NewsApi {


    @GET("everything?q=Apple&from=2021-06-16&sortBy=popularity&apiKey=1cacb7c8495e4e4f9f80004717ec63da")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int

    ): Response<NewsResponse>
}