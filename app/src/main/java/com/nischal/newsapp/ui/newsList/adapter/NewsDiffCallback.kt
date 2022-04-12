package com.nischal.newsapp.ui.newsList.adapter

import androidx.recyclerview.widget.DiffUtil
import com.nischal.newsapp.ui.newsList.models.NewsDisplayModel

/**
 * Diff callback to notify only the changed data in [NewsListAdapter]
 */
class NewsDiffCallback(
    private val oldNewsList: List<NewsDisplayModel>,
    private val newNewsList: List<NewsDisplayModel>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldNewsList.size
    }

    override fun getNewListSize(): Int {
        return newNewsList.size
    }

    /**
     * Called at the beginning of comparison.
     *
     * With this,
     * if items are the same, [NewsDiffCallback] will check [NewsDiffCallback.areContentsTheSame].
     * if items are different, onBindViewHolder of [NewsListAdapter] will be called
     */
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return try {
            return oldNewsList[oldItemPosition].id == newNewsList[newItemPosition].id
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Called if [areItemsTheSame] returns true
     *
     * With this,
     * if contents of items are the same, the [NewsListAdapter] will not call onBindViewHolder
     *
     * @return true if the contents of the items are the same or false if they are different.
     */
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return try {
            return oldNewsList[oldItemPosition] == newNewsList[newItemPosition]
        } catch (e: Exception) {
            false
        }
    }
}