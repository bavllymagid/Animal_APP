package com.evapharma.animalhealth.mainflow.feed.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostsRequest(var page: Int = 1, var keyword: String = "", var maxPage:Int = 0) : Parcelable
