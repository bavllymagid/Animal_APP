package com.evapharma.animalhealth.mainflow.feed.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Feed(
    val category: String?,
    val authorName: String?,
    val postId: Int?,
    val doctorId: String?,
    val publishDate: String?,
    val editDate: String?,
    val isPublished: Boolean?,
    val text: String?,
    val image: String?
):Parcelable