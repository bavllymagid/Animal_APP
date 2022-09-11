package com.evapharma.animalhealth.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.RoomDatabase
import com.evapharma.animalhealth.applicationflow.data.local.DB
import com.evapharma.animalhealth.authflow.data.local.LocalDataSource
import com.evapharma.animalhealth.authflow.data.remote.CustomerAuthAPI
import com.evapharma.animalhealth.authflow.data.remote.RemoteDataSource
import com.evapharma.animalhealth.authflow.data.remote.RemoteDataSourceImpl
import com.evapharma.animalhealth.authflow.data.repository.CustomerCustomerRepositoryImpl
import com.evapharma.animalhealth.authflow.domain.repository.CustomerRepository
import com.evapharma.animalhealth.data.remote.ApiRefresh
import com.evapharma.animalhealth.data.remote.RefreshRemoteDataSource
import com.evapharma.animalhealth.data.remote.RefreshRemoteDataSourceImpl
import com.evapharma.animalhealth.data.repository.RefreshRepositoryImpl
import com.evapharma.animalhealth.domain.repository.RefreshRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://andrew100-001-site1.etempurl.com/api/")
        .build()

    @Singleton
    @Provides
    fun getDB(@ApplicationContext context: Context): DB = Room.databaseBuilder(
        context.applicationContext,
        DB::class.java,
        "AnimalHealthDB"
    ).fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("User", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun getApi(retrofit: Retrofit): ApiRefresh {
        return retrofit.create(ApiRefresh::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(refresh: ApiRefresh): RefreshRemoteDataSource {
        return RefreshRemoteDataSourceImpl(refresh)
    }

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: RefreshRemoteDataSource): RefreshRepository {
        return RefreshRepositoryImpl(remoteDataSource)
    }

}