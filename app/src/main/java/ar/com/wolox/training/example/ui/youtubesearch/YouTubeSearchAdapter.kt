package ar.com.wolox.training.example.ui.youtubesearch

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ar.com.wolox.training.R
import ar.com.wolox.training.example.model.youtube.YouTubeSearchItem
import kotlinx.android.synthetic.main.search_youtube_item_adapter.view.*

class YouTubeSearchAdapter(private var items: List<YouTubeSearchItem>, private val listener: SearchYouTubeAdapterBridge) : RecyclerView.Adapter<YouTubeSearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.search_youtube_item_adapter, p0, false))
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val item = items[p1]
        p0.apply {
            p0.text.text = item.snippet?.description
            p0.title.text = item.snippet?.title
            p0.thumb.setImageURI(Uri.parse(item.snippet?.thumbnails?.default?.url))
            p0.itemView.setOnClickListener { listener.onItemClick(item) }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.vYouTubeItemText
        val title: TextView = view.vYouTubeItemTitle
        val thumb: ImageView = view.vYouTubeItemImageView
    }

    interface SearchYouTubeAdapterBridge {
        fun onItemClick(item: YouTubeSearchItem)
    }
}