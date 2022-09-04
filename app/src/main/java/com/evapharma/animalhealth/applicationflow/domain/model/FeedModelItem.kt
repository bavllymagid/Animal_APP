package com.evapharma.animalhealth.applicationflow.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedModelItem
    (
    val doctor: String,
    val doctorId: String,
    val editDate: String,
    val image: ArrayList<Byte>,
    val isPublished: Boolean,
    val postId: Int,
    val publishDate: String,
    val text: String
):Parcelable