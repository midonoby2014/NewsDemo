package com.akshay.newsapp.news.domain


import com.noby.tempotask.api.NewsApi
import com.noby.tempotask.data.models.NewsResponse
import com.noby.tempotask.data.room.NewsArticleDao
import com.noby.tempotask.data.room.NewsMapper
import com.noby.tempotask.data.room.entity.NewsArticleDb
import com.noby.tempotask.util.ViewState
import com.noby.tempotask.util.httpError
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

interface NewsRepository {


    fun getNewsArticles(searchQuery:String): Flow<ViewState<List<NewsArticleDb>>>


    suspend fun getNewsFromWebservice(): Response<NewsResponse>
}

//@Singleton
class DefaultNewsRepository @Inject constructor(
    private val newsDao: NewsArticleDao,
    private val newsService: NewsApi
) : NewsRepository, NewsMapper {

    override fun getNewsArticles(searchQuery:String): Flow<ViewState<List<NewsArticleDb>>> = flow {
        // 1. Start with loading
        emit(ViewState.loading())

        // 2. Try to fetch fresh news from web + cache if any
        val freshNews = getNewsFromWebservice()
        freshNews.body()?.articles?.toStorage()?.let(newsDao::clearAndCacheArticles)

        // 3. Get news from cache [cache is always source of truth]
        val cachedNews = newsDao.getNewsArticles(searchQuery)
        emitAll(cachedNews.map { ViewState.success(it) })
    }
        .flowOn(Dispatchers.IO)



    override suspend fun getNewsFromWebservice(): Response<NewsResponse> {
        return try {
            newsService.getNews(1,30)
        } catch (e: Exception) {
            httpError(404)
        }
    }
}
@Module
@InstallIn(SingletonComponent::class)
interface NewsRepositoryModule {
    /* Exposes the concrete implementation for the interface */
    @Binds
    fun it(it: DefaultNewsRepository): NewsRepository
}