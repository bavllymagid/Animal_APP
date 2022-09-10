package com.evapharma.animalhealth.authflow.di

import android.content.Context
import android.content.SharedPreferences
import com.evapharma.animalhealth.authflow.data.local.LocalDataSource
import com.evapharma.animalhealth.authflow.data.local.LocalDataSourceImpl
import com.evapharma.animalhealth.authflow.data.remote.CustomerAuthAPI
import com.evapharma.animalhealth.authflow.data.remote.RemoteDataSource
import com.evapharma.animalhealth.authflow.data.remote.RemoteDataSourceImpl
import com.evapharma.animalhealth.authflow.data.repository.CustomerCustomerRepositoryImpl
import com.evapharma.animalhealth.authflow.domain.repository.CustomerRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideLocalDataSource(sharedPreferences: SharedPreferences): LocalDataSource{
        return LocalDataSourceImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource): CustomerRepository{
        return CustomerCustomerRepositoryImpl(remoteDataSource,localDataSource)
    }

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("User", Context.MODE_PRIVATE)
    }
}