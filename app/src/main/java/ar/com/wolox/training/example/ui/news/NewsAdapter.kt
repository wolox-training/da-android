package ar.com.wolox.training.example.ui.news

import android.net.Uri
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ar.com.wolox.training.R
import ar.com.wolox.training.example.model.News
import ar.com.wolox.training.example.utils.getTimeFromDate
import kotlinx.android.synthetic.main.news_item_adapter.view.*

class NewsAdapter(private var items: List<News>, private var listener: NewsAdapterBridge) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var offset: Int = -1

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.news_item_adapter, p0, false))
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val news = items[p1]

        p0.apply {
            title.text = news.title
            text.text = news.text
            timeCreated.text = getTimeFromDate(news.createdAt)
            picture.setImageURI(Uri.parse(news.picture))
            toggleLikeImage(news.like, itemView, likes)
            itemView.setOnClickListener { listener.onItemClick(news, picture, p1) }
        }

        setupEndlessRecyclerView(p0)
    }

    private fun toggleLikeImage(likes: Boolean, view: View, imageView: ImageView) {
        when {
            likes -> imageView.setImageDrawable(ContextCompat.getDrawable(view.context, R.drawable.ic_like_on))
            else -> imageView.setImageDrawable(ContextCompat.getDrawable(view.context, R.drawable.ic_like_off))
        }
    }

    private fun setupEndlessRecyclerView(holder: ViewHolder) {
        if (holder.adapterPosition == itemCount - 1 && holder.adapterPosition != offset) {
            offset = holder.adapterPosition
            listener.loadMore()
        }
    }

    fun addItems(items: List<News>?) {
        val list = this.items.toMutableList()
        val start = this.items.count()
        items?.let { list.addAll(it) }
        this.items = list
        notifyItemRangeInserted(start, items?.count() ?: 0)

        offset = items?.size ?: 0
    }

    fun refreshCard(position: Int, like: Boolean) {
        val news = this.items[position]
        news.like = like
        notifyItemChanged(position)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val picture: ImageView = view.vNewsItemImageView
        val title: TextView = view.vNewsItemTitle
        val text: TextView = view.vNewsItemText
        val timeCreated: TextView = view.vNewsItemCreatedAt
        val likes: ImageView = view.vNewsItemLikesImageView
    }

    interface NewsAdapterBridge {
        fun loadMore()
        fun onItemClick(news: News, imageView: ImageView, position: Int)
    }
}