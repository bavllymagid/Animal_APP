package com.evapharma.animalhealth.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.RoomDatabase
import com.evapharma.animalhealth.applicationflow.data.local.DB
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
}