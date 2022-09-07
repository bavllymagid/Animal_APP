package com.evapharma.animalhealth.mainflow.feed.presentation.adapters


import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.application.AnimalHealthApp
import com.evapharma.animalhealth.application.AnimalHealthApp_GeneratedInjector
import com.evapharma.animalhealth.mainflow.feed.domain.model.Feed
import com.evapharma.animalhealth.mainflow.feed.utils.FeedDiffUtils
import com.evapharma.animalhealth.databinding.FeedItemBinding
import com.evapharma.animalhealth.mainflow.feed.utils.DateConverter
import dagger.hilt.android.qualifiers.ApplicationContext

class FeedAdapter(private val onItemSelected: OnItemSelected) : ListAdapter<Feed, FeedAdapter.FeedViewHolder>(FeedDiffUtils()) {

    class FeedViewHolder(val binding: FeedItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(
            FeedItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val post = getItem(position)
        holder.binding.apply {
            titleTv.text = post.authorName
            body.text = post.text
            postImg.setImageBitmap(BitmapFactory.decodeResource(AnimalHealthApp.appContext.resources, R.drawable.doctor))
            date.text = DateConverter.covertTimeToText(post.publishDate)
        }
        holder.itemView.setOnClickListener{
            if(post.category == "Article"){
                onItemSelected.onItemClicked(post)
                setClickable(holder.itemView)
            }
        }
    }

    private fun setClickable(view: View?) {
        if (view != null) {
            view.isClickable = true
            view.startAnimation(clickAnimation())
        }
    }

    private fun clickAnimation(): AlphaAnimation {
        return AlphaAnimation(1f, 0.4f)
    }

    interface OnItemSelected{
        fun onItemClicked(feedObject:Feed)
    }


}