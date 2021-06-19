package com.noby.tempotask.data.room

import com.noby.tempotask.data.models.Article
import com.noby.tempotask.data.room.entity.NewsArticleDb



interface NewsMapper : Mapper<NewsArticleDb, Article> {

        override fun NewsArticleDb.toRemote(): Article {
            return Article(
                author = author,
                title = title,
                description = description,
                url = url,
                urlToImage = urlToImage,
                publishedAt = publishedAt,
                content = content,
                //source = Article.Source(source.id, source.name)
            )
        }
        override fun Article.toStorage(): NewsArticleDb {
            return NewsArticleDb(
                author = author,
                title = title,
                description = description,
                url = url,
                urlToImage = urlToImage,
                publishedAt = publishedAt,
                content = content,
                //source = NewsArticleDb.Source(source.id, source.name)
            )
        }
    }