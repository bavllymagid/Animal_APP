package com.evapharma.animalhealth.mainflow.booking.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingModel

@Dao
interface BookingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheBookings(bookingList: List<BookingModel>)

    @Query("select * from BookingModel")
    suspend fun getBookings() : List<BookingModel>
}