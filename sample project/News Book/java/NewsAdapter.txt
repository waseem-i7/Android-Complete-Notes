package com.mwi7.newsbook

import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.news_item_layout.view.*

class NewsAdapter(private val Newsess: List<News>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item_layout,parent,false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(Newsess[position])
        holder.itemView.setOnClickListener { v->
            val context = v.context
            val url=Newsess[position].url
            val builder = CustomTabsIntent.Builder()
            builder.setToolbarColor(Color.parseColor("#B30000"))
            val customTabsIntent = builder.build();
            customTabsIntent.launchUrl(context, Uri.parse(url));
        }
    }

    override fun getItemCount(): Int {
        return Newsess.count()
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(news : News){
            itemView.title.text=news.title
            itemView.author.text=news.author
            Glide.with(itemView.context).load(news.urlToImage).into(itemView.imageView)
        }
    }


}