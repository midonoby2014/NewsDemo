package com.noby.tempotask.data.room

import androidx.room.*
import com.noby.tempotask.data.room.entity.NewsArticleDb
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ahmed Noby Ahmed on 6/18/21.
 */
@Dao
interface NewsArticleDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<NewsArticleDb>)

    @Query("DELETE FROM news_article")
    fun clearAllArticles()

    @Transaction
    fun clearAndCacheArticles(articles: List<NewsArticleDb>) {
        clearAllArticles()
        insertArticles(articles)
    }


    @Query("SELECT * FROM news_article WHERE title LIKE '%'|| :searchQuery || '%'")
    fun getNewsArticles(searchQuery:String): Flow<List<NewsArticleDb>>
//    @Query("SELECT * FROM news_article")
//    fun getNewsArticles(): Flow<List<NewsArticleDb>>
}