package com.evapharma.animalhealth.applicationflow.utils

import androidx.recyclerview.widget.DiffUtil
import com.evapharma.animalhealth.applicationflow.domain.model.FeedModel

class FeedDiffUtils :DiffUtil.ItemCallback<FeedModel>(){
    override fun areItemsTheSame(oldItem: FeedModel, newItem: FeedModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FeedModel, newItem: FeedModel): Boolean {
        return oldItem == newItem
    }
}