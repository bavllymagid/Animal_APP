package com.evapharma.animalhealth.mainflow.feed.presentation.adapters


import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.application.AnimalHealthApp
import com.evapharma.animalhealth.databinding.BookDoctorItemBinding
import com.evapharma.animalhealth.databinding.BookItemBinding
import com.evapharma.animalhealth.databinding.FeedItemBinding
import com.evapharma.animalhealth.mainflow.feed.domain.model.Feed
import com.evapharma.animalhealth.mainflow.feed.utils.FeedDiffUtils
import com.evapharma.animalhealth.mainflow.utils.DateConverter
import com.evapharma.animalhealth.utils.ImageLoader

class FeedAdapter(private val onItemSelected: OnItemSelected) : ListAdapter<Feed, RecyclerView.ViewHolder>(FeedDiffUtils()) {

    class FeedViewHolder(val binding: FeedItemBinding) : RecyclerView.ViewHolder(binding.root)
    class BookViewHolder(val binding: BookDoctorItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == 1) {
            return BookViewHolder(
                BookDoctorItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }else{
            return FeedViewHolder(
                FeedItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post = getItem(position)
        if(holder is FeedViewHolder){
            holder.binding.apply {
                if(post.category == "Article"){
                    postImg.visibility = View.VISIBLE
                    titleTv.text = post.authorName
                    body.visibility = View.GONE
                    ImageLoader.loadImageIntoImageView(post.image?:"",postImg)
                }else{
                    titleTv.text = post.authorName
                    body.text = post.text
                    body.visibility = View.VISIBLE
                    if(post.image != null){
                        postImg.visibility = View.VISIBLE
                        ImageLoader.loadImageIntoImageView(post.image,postImg)
                    }else
                    {
                        postImg.visibility = View.GONE
                    }
                }
                date.text = DateConverter.covertTimeToText(post.publishDate)
            }
            holder.itemView.setOnClickListener{
                if(post.category == "Article"){
                    onItemSelected.onItemClicked(post)
                    setClickable(holder.itemView)
                }
            }
        }else{
            holder.itemView.findViewById<CardView>(R.id.book_btn).setOnClickListener {
                onItemSelected.onFirstClicked()
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
        fun onFirstClicked()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) 1 else 2
    }


}