package com.evapharma.animalhealth.applicationflow.di

import android.content.Context
import com.evapharma.animalhealth.applicationflow.data.remote.DoctorsInterface
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
class AppModule {


    @Singleton
    @Provides
    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("www.google.com")
        .build()

    @Singleton
    @Provides
    fun getDoctorInterface(retrofit: Retrofit) : DoctorsInterface = retrofit.create(DoctorsInterface::class.java)


}