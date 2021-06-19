package com.noby.tempotask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.noby.tempotask.data.models.Article
import com.noby.tempotask.data.room.entity.NewsArticleDb
import com.noby.tempotask.databinding.ItemArticleBinding

/**
 * Created by Ahmed Noby Ahmed on 6/17/21.
 */
class ArticlesAdapter (private val listener :onItemClickListener)
    : ListAdapter<NewsArticleDb, ArticlesAdapter.ArticleViewHolder>(ArticleDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ArticleViewHolder(ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ArticleViewHolder(private val binder: ItemArticleBinding) : RecyclerView.ViewHolder(binder.root) {

        fun bind(article: NewsArticleDb) {
            Glide.with(itemView)
                .load(article.urlToImage)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(binder.imgNews)
            binder.article = article
            binder.root.setOnClickListener { listener.onItemClick(article) }
            binder.executePendingBindings()
        }
    }

    interface onItemClickListener{
        fun onItemClick (article: NewsArticleDb)


    }

    private object ArticleDiff : DiffUtil.ItemCallback<NewsArticleDb>() {

        override fun areItemsTheSame(oldItem: NewsArticleDb, newItem: NewsArticleDb) = oldItem == newItem

        override fun areContentsTheSame(oldItem: NewsArticleDb, newItem: NewsArticleDb): Boolean {
            // In this case, if items are the same then content will always be the same
            return true
        }

    }
}
