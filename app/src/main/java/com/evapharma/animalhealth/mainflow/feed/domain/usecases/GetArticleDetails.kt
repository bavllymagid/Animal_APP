package com.evapharma.animalhealth.mainflow.feed.domain.usecases

import com.evapharma.animalhealth.mainflow.feed.domain.model.Article
import com.evapharma.animalhealth.mainflow.feed.domain.repository.FeedRepository
import javax.inject.Inject

class GetArticleDetails @Inject constructor(val repository: FeedRepository) {
    suspend fun execute(id:Int):Article?{
        return repository.getArticleById(id)
    }
}