package com.evapharma.animalhealth.mainflow.feed.domain.model

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Feed(
    val category: String,
    val doctorId: String,
    val editDate: String,
    val image: Bitmap?,
    val isPublished: Boolean,
    val postId: Int,
    val publishDate: String,
    val text: String
):Parcelable