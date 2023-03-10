package com.evapharma.animalhealth.mainflow.feed.data.remote

import android.util.Log
import com.evapharma.animalhealth.mainflow.feed.domain.model.Article
import com.evapharma.animalhealth.mainflow.feed.domain.model.Feed
import com.evapharma.animalhealth.mainflow.feed.domain.model.FeedX
import com.evapharma.animalhealth.mainflow.feed.domain.model.PostsRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class FeedRemoteDataSourceImpl @Inject constructor(private val api: FeedApi) :
    FeedRemoteDataSource {

    override suspend fun getPosts(postsRequest: PostsRequest): FeedX? {
        try {
            val response = api.getPosts(postsRequest.page)

            if(response.isSuccessful){
                return response.body()
            }else{
                Log.d("MyApp", response.code().toString()+ " " + response.message())
            }
        }catch (e:Exception){
            Log.d("MyApp", e.message.toString())
        }
        return null
    }

    override suspend fun getPostsByKeyword(postsRequest: PostsRequest): FeedX? {


            val response = api.getPostsByKeyWord(postsRequest.keyword,postsRequest.page)

            if(response.isSuccessful){
                return response.body()
            }else{
                Log.d("MyApp", response.code().toString()+ " " + response.message())
            }

        return null
    }

    override suspend fun getArticleById(id: Int): Article? {
        return api.getArticleById(id).body()
    }
}