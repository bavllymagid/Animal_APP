package com.evapharma.animalhealth.mainflow.feed.data.remote

import com.evapharma.animalhealth.mainflow.feed.domain.model.Feed
import com.evapharma.animalhealth.mainflow.feed.domain.model.PostsRequest
import javax.inject.Inject

class FeedRemoteDataSourceImpl @Inject constructor(private val api: FeedApi) :
    FeedRemoteDataSource {
    override suspend fun getPosts(postsRequest: PostsRequest): List<Feed> {

        val response = api.getPosts(postsRequest.page)
        val list = ArrayList<Feed>()

        if(response.isSuccessful){
            for (item in response.body()?:ArrayList()){
                list.add(item)
            }
        }
        return list
    }

    override suspend fun getPostsByKeyword(postsRequest: PostsRequest): List<Feed> {

        val response = api.getPostsByKeyWord(postsRequest.keyword)
        val list = ArrayList<Feed>()

        if(response.isSuccessful){
            for (item in response.body()?:ArrayList()){
                list.add(item)
            }
        }
        return list
    }
}