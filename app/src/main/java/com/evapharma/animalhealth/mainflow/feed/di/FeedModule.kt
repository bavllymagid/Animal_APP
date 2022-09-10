package com.evapharma.animalhealth.mainflow.feed.di

import com.evapharma.animalhealth.mainflow.feed.data.remote.FeedApi
import com.evapharma.animalhealth.mainflow.feed.data.remote.FeedRemoteDataSource
import com.evapharma.animalhealth.mainflow.feed.data.remote.FeedRemoteDataSourceImpl
import com.evapharma.animalhealth.mainflow.feed.domain.repository.FeedRepository
import com.evapharma.animalhealth.mainflow.feed.data.repository.FeedRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FeedModule {

    @Singleton
    @Provides
    fun getApi(retrofit: Retrofit): FeedApi = retrofit.create(FeedApi::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(api: FeedApi): FeedRemoteDataSource = FeedRemoteDataSourceImpl(api)


    @Singleton
    @Provides
    fun provideRepository(feedRemoteDataSource: FeedRemoteDataSource): FeedRepository = FeedRepositoryImpl(feedRemoteDataSource)


}