package com.evapharma.animalhealth.mainflow.feed.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Feed(
    val authorName: String,
    val category: String,
    val editDate: String,
    val id: Int,
    val image: String,
    val isPublished: Boolean,
    val publishDate: String,
    val text: String
):Parcelable