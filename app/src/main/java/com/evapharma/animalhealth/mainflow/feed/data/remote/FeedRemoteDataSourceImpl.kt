package com.evapharma.animalhealth.mainflow.feed.data.remote

import android.util.Log
import com.evapharma.animalhealth.mainflow.feed.domain.model.Feed
import com.evapharma.animalhealth.mainflow.feed.domain.model.PostsRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class FeedRemoteDataSourceImpl @Inject constructor(private val api: FeedApi) :
    FeedRemoteDataSource {

    override suspend fun getPosts(postsRequest: PostsRequest): List<Feed> {
        val list = ArrayList<Feed>()
        try {
            val response = api.getPosts(postsRequest.page)

            if(response.isSuccessful){
                for(item in response.body()?.articlesPosts?:ArrayList()){
                    list.add(item)
                }
            }else{
                Log.d("MyApp", response.code().toString()+ " " + response.message())
            }
        }catch (e:Exception){
            Log.d("MyApp", e.message.toString())
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