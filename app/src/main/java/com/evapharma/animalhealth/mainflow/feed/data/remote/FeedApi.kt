package com.evapharma.animalhealth.mainflow.feed.data.remote

import com.evapharma.animalhealth.mainflow.feed.domain.model.Feed
import com.evapharma.animalhealth.mainflow.feed.domain.model.FeedX
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface FeedApi {

    @GET("HomePage/DisplayPagePosts")
    suspend fun getPosts(@Query("xPage") pageNum: Int):Response<FeedX>

    @GET("Search/Search")
    suspend fun getPostsByKeyWord(@Query("KeyWord") keyWord:String): Response<List<Feed>>
}