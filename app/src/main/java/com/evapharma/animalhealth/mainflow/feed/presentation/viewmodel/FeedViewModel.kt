package com.evapharma.animalhealth.mainflow.feed.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.evapharma.animalhealth.mainflow.feed.domain.model.Article
import com.evapharma.animalhealth.mainflow.feed.domain.model.FeedX
import com.evapharma.animalhealth.mainflow.feed.domain.model.PostsRequest
import com.evapharma.animalhealth.mainflow.feed.domain.usecases.GetArticleDetails
import com.evapharma.animalhealth.mainflow.feed.domain.usecases.GetPosts
import com.evapharma.animalhealth.mainflow.feed.domain.usecases.SearchByKeyword
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(private val getPostsUseCase: GetPosts,
                                        private val searchByKeywordUseCase: SearchByKeyword,
                                        private val getArticleDetails: GetArticleDetails) : ViewModel(){

    fun getPosts(postsRequest: PostsRequest):LiveData<FeedX?>{
        return liveData {
            emit(getPostsUseCase.execute(postsRequest))
        }
    }

    suspend fun getSearchResult(postsRequest: PostsRequest):FeedX?{
        return searchByKeywordUseCase.execute(postsRequest)

    }

    suspend fun getArticleBody(id:Int):Article?{
        return getArticleDetails.execute(id)
    }

}