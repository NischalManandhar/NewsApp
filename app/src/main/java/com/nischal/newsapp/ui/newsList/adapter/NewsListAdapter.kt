package com.nischal.newsapp.ui.newsList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nischal.newsapp.base.ViewHolderBinder
import com.nischal.newsapp.databinding.ItemNewsBinding
import com.nischal.newsapp.ui.newsList.NewsViewModel
import com.nischal.newsapp.ui.newsList.models.NewsDisplayModel
import com.nischal.newsapp.utils.setImageWithThumb
import kotlinx.coroutines.*
import toNewsTimeFormat

class NewsListAdapter(
    val newsList: MutableList<NewsDisplayModel> = mutableListOf(),
    val newsViewModel: NewsViewModel
) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
    // Initialise view binding here.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemViewBinding =
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemViewBinding = itemViewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = newsList.size

    /**
     * Set news list in the recycler view adapter via [NewsDiffCallback]
     */
    private var setNewsListJob: Job? = null
    fun setNewsList(newNewsList: List<NewsDisplayModel>) {
        //Cancel any previously running jobs
        setNewsListJob?.cancel()
        setNewsListJob = CoroutineScope(Dispatchers.Default).launch {
            val diffResult = DiffUtil.calculateDiff(NewsDiffCallback(newsList, newNewsList))
            withContext(Dispatchers.Main) {
                diffResult.dispatchUpdatesTo(this@NewsListAdapter)
                newsList.clear()
                newsList.addAll(newNewsList)
            }
        }
    }

    inner class ViewHolder(private val itemViewBinding: ItemNewsBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root), ViewHolderBinder {

        override fun bind(position: Int) {
            val newsDisplayModel = newsList[position]
            with(itemViewBinding) {
                ivNews.setImageWithThumb(newsDisplayModel.getSmallestImageUrl())
                tvHeadline.text = newsDisplayModel.headline
                tvAbstract.text = newsDisplayModel.theAbstract
                tvByLine.text = newsDisplayModel.getByLineToDisplay()
                tvTimestamp.text = newsDisplayModel.timeStamp?.toNewsTimeFormat()
                itemViewBinding.root.setOnClickListener {
                    newsViewModel.onNewsItemClicked(newsDisplayModel)
                }
            }
        }
    }
}