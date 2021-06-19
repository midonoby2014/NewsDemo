package com.noby.tempotask.data.room.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by Ahmed Noby Ahmed on 6/18/21.
 */
//@Entity(tableName = "news_article")
//data class NewsArticleDb(
//
//        @PrimaryKey(autoGenerate = true)
//        val id: Int = 0,
//      //  @ColumnInfo(name =Column.author)
//        val author: String? = null,
//      //  @ColumnInfo(name = Column.title)
//        val title: String? = null,
//       // @ColumnInfo(name = Column.description)
//        val description: String? = null,
//       // @ColumnInfo(name = Column.url)
//        val url: String? = null,
//      //  @ColumnInfo(name = Column.urlToImage)
//        val urlToImage: String? = null,
//      //  @ColumnInfo(name = Column.publishedAt)
//        val publishedAt: String? = null,
//     //   @Embedded(prefix = "source_")
//        val source: Source,
//      //  @ColumnInfo(name = Column.content)
//        val content: String? = null
//) {
//
//    data class Source(
//         //   @ColumnInfo(name = Column.sourceId)
//            val id: String? = null,
//          //  @ColumnInfo(name = Column.sourceName)
//            val name: String? = null
//    )
//
//}

@Entity(tableName = "news_article")
@Parcelize
data class NewsArticleDb(
    @PrimaryKey(autoGenerate = true) val id: Int  = 0,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,

    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
    //val source: Source

) : Parcelable {


}