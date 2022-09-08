package com.evapharma.animalhealth.mainflow.booking.di


import com.evapharma.animalhealth.applicationflow.data.local.DB
import com.evapharma.animalhealth.mainflow.booking.data.local.BookingDao
import com.evapharma.animalhealth.mainflow.booking.data.local.BookingLocalDataSource
import com.evapharma.animalhealth.mainflow.booking.data.local.BookingLocalDataSourceImpl
import com.evapharma.animalhealth.mainflow.booking.data.remote.BookingApi
import com.evapharma.animalhealth.mainflow.booking.data.remote.BookingRemoteDataSource
import com.evapharma.animalhealth.mainflow.booking.data.remote.BookingRemoteDataSourceImpl
import com.evapharma.animalhealth.mainflow.booking.data.repository.BookingRepositoryImpl
import com.evapharma.animalhealth.mainflow.booking.domain.repository.BookingRepository
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

    @Singleton
    @Provides
    fun getRemoteDataSource(api: BookingApi) : BookingRemoteDataSource =
        BookingRemoteDataSourceImpl(api)

    @Singleton
    @Provides
    fun getLocalDataSource(db: BookingDao) : BookingLocalDataSource =
        BookingLocalDataSourceImpl(db)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: BookingRemoteDataSource , localDataSource: BookingLocalDataSource) : BookingRepository =
        BookingRepositoryImpl(remoteDataSource,localDataSource)

    @Singleton
    @Provides
    fun provideDao(db: DB):BookingDao =
        db.BookingDao()

}