package com.evapharma.animalhealth.mainflow.feed.domain.usecases

import com.evapharma.animalhealth.mainflow.feed.domain.repository.FeedRepository
import com.evapharma.animalhealth.mainflow.feed.domain.model.FeedX
import com.evapharma.animalhealth.mainflow.feed.domain.model.PostsRequest
import javax.inject.Inject

class GetPosts @Inject constructor(val repository: FeedRepository){
    suspend fun execute(postsRequest: PostsRequest) : FeedX?{
        return repository.getPosts(postsRequest)
    }
}