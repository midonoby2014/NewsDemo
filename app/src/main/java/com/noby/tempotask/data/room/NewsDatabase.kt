package com.noby.tempotask.data.room

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.noby.tempotask.data.room.entity.NewsArticleDb
import com.noby.tempotask.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by Ahmed Noby Ahmed on 6/18/21.
 */
@Database (entities = [NewsArticleDb::class] , version = 1)
abstract class NewsDatabase : RoomDatabase() {

    /**
     * Get news article DAO
     */
    abstract fun newsArticlesDao(): NewsArticleDao

//    companion object {
//
//        private const val databaseName = "news-db"
//
//        fun buildDefault(context: Context) =
//            Room.databaseBuilder(context, NewsDatabase::class.java, databaseName)
//                .build()
//    }

//    class  Callback @Inject constructor(
//        @ApplicationScope private  val applicationScope : CoroutineScope
//    ):RoomDatabase.Callback (){

//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//
//        }
  //  }

}