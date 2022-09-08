package com.evapharma.animalhealth.applicationflow.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.evapharma.animalhealth.mainflow.booking.data.local.BookingDao
import com.evapharma.animalhealth.mainflow.booking.domain.model.BookingModel
import com.evapharma.animalhealth.mainflow.booking.domain.model.DateTimeSlot
import com.evapharma.animalhealth.mainflow.booking.domain.model.DoctorModel


@Database(entities = [BookingModel::class], version = 2, exportSchema = false)
abstract class DB : RoomDatabase() {
    abstract fun BookingDao():BookingDao
}