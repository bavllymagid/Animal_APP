package com.evapharma.animalhealth.mainflow.feed.data.remote

import com.evapharma.animalhealth.mainflow.feed.domain.model.Feed
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface FeedApi {
    @GET("HomePage/DisplayPagePosts")
    suspend fun getPosts(@Query("?xPage") pageNum: Int):Response<List<Feed>>

    @GET("Search/Search")
    suspend fun getPostsByKeyWord(@Query("KeyWord") keyWord:String): Response<List<Feed>>
}