package com.evapharma.animalhealth.mainflow.booking.di


import com.evapharma.animalhealth.mainflow.booking.data.remote.BookingApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BookingModule {
    @Singleton
    @Provides
    fun getRemoteInterface(retrofit: Retrofit) : BookingApi = retrofit.create(BookingApi::class.java)
}