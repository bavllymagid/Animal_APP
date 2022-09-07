package com.evapharma.animalhealth.mainflow.feed.domain.model

data class FeedX(
    val articlesPosts: List<Feed>,
    val currentPage: Int,
    val totalPages: Int
)