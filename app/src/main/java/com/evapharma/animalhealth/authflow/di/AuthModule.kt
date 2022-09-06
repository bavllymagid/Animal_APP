package com.evapharma.animalhealth.authflow.di

import com.evapharma.animalhealth.authflow.data.remote.CustomerAuthAPI
import com.evapharma.animalhealth.authflow.data.remote.RemoteDataSource
import com.evapharma.animalhealth.authflow.data.remote.RemoteDataSourceImpl
import com.evapharma.animalhealth.authflow.data.repo.CustomerRepoImpl
import com.evapharma.animalhealth.authflow.domain.repo.Repo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

    @Provides
    @Singleton
    fun getApi(retrofit: Retrofit):CustomerAuthAPI{
        return retrofit.create(CustomerAuthAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(customerAuthAPI: CustomerAuthAPI): RemoteDataSource{
        return RemoteDataSourceImpl(customerAuthAPI)
    }

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: RemoteDataSource): Repo{
        return CustomerRepoImpl(remoteDataSource)
    }

}