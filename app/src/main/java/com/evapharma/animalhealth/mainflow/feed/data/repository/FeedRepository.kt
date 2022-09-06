package com.evapharma.animalhealth.mainflow.feed.data.repository

import com.evapharma.animalhealth.mainflow.feed.domain.model.Feed
import com.evapharma.animalhealth.mainflow.feed.domain.model.FeedX
import com.evapharma.animalhealth.mainflow.feed.domain.model.PostsRequest

interface FeedRepository {
    suspend fun getPosts(postsRequest: PostsRequest) : FeedX?
    suspend fun getPostsByKeyword(postsRequest: PostsRequest) : List<Feed>
}