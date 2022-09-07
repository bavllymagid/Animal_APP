package com.evapharma.animalhealth.mainflow.feed.data.remote

import com.evapharma.animalhealth.mainflow.feed.domain.model.Feed
import com.evapharma.animalhealth.mainflow.feed.domain.model.PostsRequest

interface FeedRemoteDataSource {
    suspend fun getPosts(postsRequest: PostsRequest): List<Feed>
    suspend fun getPostsByKeyword(postsRequest: PostsRequest): List<Feed>
}