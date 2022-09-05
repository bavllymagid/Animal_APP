package com.evapharma.animalhealth.mainflow.feed.utils

import androidx.recyclerview.widget.DiffUtil
import com.evapharma.animalhealth.mainflow.feed.domain.model.Feed

class FeedDiffUtils :DiffUtil.ItemCallback<Feed>(){
    override fun areItemsTheSame(oldItem: Feed, newItem: Feed): Boolean {
        return oldItem.postId == newItem.postId
    }

    override fun areContentsTheSame(oldItem: Feed, newItem: Feed): Boolean {
        return oldItem == newItem
    }
}