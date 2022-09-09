package com.evapharma.animalhealth.mainflow.feed.data.remote

import com.evapharma.animalhealth.mainflow.feed.domain.model.Article
import com.evapharma.animalhealth.mainflow.feed.domain.model.Feed
import com.evapharma.animalhealth.mainflow.feed.domain.model.FeedX
import com.evapharma.animalhealth.mainflow.feed.domain.model.PostsRequest
import retrofit2.Response
import retrofit2.http.Query

interface FeedRemoteDataSource {
    suspend fun getPosts(postsRequest: PostsRequest): FeedX?
    suspend fun getPostsByKeyword(postsRequest: PostsRequest): FeedX?
    suspend fun getArticleById(@Query("Id") id:Int) : Article?
}