package com.evapharma.animalhealth.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.evapharma.animalhealth.R
import com.evapharma.animalhealth.application.AnimalHealthApp

object ImageLoader {
    fun loadImageIntoImageView(url:String, view:ImageView){
            Glide.with(AnimalHealthApp.appContext)
                .load(url)
                .placeholder(R.drawable.doctor)
                .error(R.drawable.error_image)
                .into(view)
    }
}