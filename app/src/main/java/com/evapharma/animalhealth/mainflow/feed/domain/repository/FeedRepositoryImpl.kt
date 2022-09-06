package com.evapharma.animalhealth.mainflow.feed.domain.repository

import com.evapharma.animalhealth.mainflow.feed.data.remote.FeedRemoteDataSource
import com.evapharma.animalhealth.mainflow.feed.data.repository.FeedRepository
import com.evapharma.animalhealth.mainflow.feed.domain.model.Feed
import com.evapharma.animalhealth.mainflow.feed.domain.model.FeedX
import com.evapharma.animalhealth.mainflow.feed.domain.model.PostsRequest

class FeedRepositoryImpl(private val feedRemoteDataSource: FeedRemoteDataSource) : FeedRepository {
    override suspend fun getPosts(postsRequest: PostsRequest) : FeedX? {
        return feedRemoteDataSource.getPosts(postsRequest)
    }

    override suspend fun getPostsByKeyword(postsRequest: PostsRequest): List<Feed> {
        return feedRemoteDataSource.getPostsByKeyword(postsRequest)
    }
}