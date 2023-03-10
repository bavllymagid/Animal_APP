package com.evapharma.animalhealth.mainflow.booking.data.local

import androidx.room.*
import com.evapharma.animalhealth.mainflow.booking.data.local.model.LocalBooking
import com.evapharma.animalhealth.mainflow.booking.data.mappers.LocalToBooking
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingModel

@Dao
interface BookingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheBookings(bookingList: List<LocalBooking>)

    @Query("select * from LocalBooking")
    suspend fun getBookings() : List<LocalBooking>

    @Query("DELETE FROM LocalBooking")
    suspend fun dropTable()
}