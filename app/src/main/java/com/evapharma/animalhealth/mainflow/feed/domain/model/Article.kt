package com.evapharma.animalhealth.mainflow.feed.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val adminCreationId: String,
    val adminEditedByID: String,
    val articleId: Int,
    val authorName: String,
    val body: String,
    val category: String,
    val editDate: String,
    val heading: String,
    val image: String,
    val isPublished: Boolean,
    val publishDate: String,
    val reference: ArrayList<String>,
    val specialization: String,
    val title: String
):Parcelable