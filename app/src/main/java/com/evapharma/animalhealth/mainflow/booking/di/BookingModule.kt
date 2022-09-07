package com.evapharma.animalhealth.mainflow.booking.di


import com.evapharma.animalhealth.mainflow.booking.data.remote.BookingRemoteInterface
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
    fun getRemoteInterface(retrofit: Retrofit) : BookingRemoteInterface = retrofit.create(BookingRemoteInterface::class.java)
}