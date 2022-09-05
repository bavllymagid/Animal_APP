package com.evapharma.animalhealth.authflow.di

import com.evapharma.animalhealth.authflow.data.remote.CustomerAuthAPI
import com.evapharma.animalhealth.authflow.data.remote.RemoteDataSource
import com.evapharma.animalhealth.authflow.data.remote.RemoteDataSourceImpl
import com.evapharma.animalhealth.authflow.data.repo.CustomerRepoImpl
import com.evapharma.animalhealth.authflow.domain.repo.Repo
import com.evapharma.animalhealth.authflow.domain.usecases.Register
import com.evapharma.animalhealth.di.MainModule_GetRetrofitFactory.getRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

    @Provides
    @Singleton
    suspend fun getCustomerAuthAPI():CustomerAuthAPI{
        return Retrofit.Builder()
            .baseUrl("http://davidsamy-001-site1.dtempurl.com/api")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CustomerAuthAPI::class.java)
    }

    @Provides
    @Singleton
    suspend fun provideRemoteDataSource(customerAuthAPI: CustomerAuthAPI): RemoteDataSource{
        return RemoteDataSourceImpl(customerAuthAPI)
    }

    @Provides
    @Singleton
    suspend fun provideRepository(remoteDataSource: RemoteDataSource): Repo{
        return CustomerRepoImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    suspend fun provideRegisterationUseCase(repo: Repo): Register{
        return Register(repo)
    }




}