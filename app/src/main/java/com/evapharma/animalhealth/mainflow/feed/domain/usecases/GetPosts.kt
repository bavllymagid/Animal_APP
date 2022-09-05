package com.evapharma.animalhealth.mainflow.feed.domain.usecases

import com.evapharma.animalhealth.mainflow.feed.data.repository.FeedRepository
import com.evapharma.animalhealth.mainflow.feed.domain.model.Feed
import com.evapharma.animalhealth.mainflow.feed.domain.model.PostsRequest
import javax.inject.Inject

class GetPosts @Inject constructor(val repository: FeedRepository){
    suspend fun execute(postsRequest: PostsRequest) : List<Feed>{
        return repository.getPosts(postsRequest)
    }
}