package com.noby.tempotask.di

import android.app.Application
import androidx.room.Room
import com.noby.tempotask.api.NewsApi
import com.noby.tempotask.data.room.NewsArticleDao
import com.noby.tempotask.data.room.NewsDatabase
import com.noby.tempotask.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


/**
 * Created by Ahmed Noby Ahmed on 6/16/21.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
         val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        return client
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    @Singleton
    @Provides
    fun provideNews (retrofit: Retrofit) : NewsApi =
        retrofit.create(NewsApi::class.java)


//    @Singleton
//    @Provides
//    fun provideDb(app: Application): NewsDatabase = NewsDatabase.buildDefault(app)

    @Provides
    @Singleton
    fun provideDatabase (
        app : Application
       // callback :NewsDatabase.Callback
    ) =
        Room.databaseBuilder(app  , NewsDatabase::class.java , "news_database")
            .fallbackToDestructiveMigration()
           // .addCallback(callback)
            .build()

 //   @Singleton
    @Provides
    fun provideUserDao(db: NewsDatabase): NewsArticleDao = db.newsArticlesDao()


    @ApplicationScope
    @Provides
    @Singleton
    fun providesApplicationScope () =  CoroutineScope(SupervisorJob())



}



@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope