package com.evapharma.animalhealth.mainflow.booking.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evapharma.animalhealth.mainflow.booking.domain.model.*
import com.evapharma.animalhealth.mainflow.booking.domain.usecases.BookAnAppointment
import com.evapharma.animalhealth.mainflow.booking.domain.usecases.GetDoctorList
import com.evapharma.animalhealth.mainflow.booking.domain.usecases.GetDoctorsAvailDateTime
import com.evapharma.animalhealth.mainflow.booking.domain.usecases.GetBookings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val bookAnAppointment: BookAnAppointment,
    private val getBookings: GetBookings,
    private val getDoctorsAvailDateTime: GetDoctorsAvailDateTime,
    private val getDoctorList: GetDoctorList,
) : ViewModel() {

    suspend fun bookAppointment(token:String, appointment: AppointmentModel):Boolean{
        return bookAnAppointment.execute(token ,appointment)
    }

    suspend fun getUpComingBookings(id:String):List<BookingModel>{
        return getBookings.execute(id)
    }

    suspend fun getDoctorDays(id:String):List<String>{
        return getDoctorsAvailDateTime.executeDays(id)
    }

    suspend fun getDoctorScheduleForADay(id:String, day:String):List<DateTimeSlot>{
        return getDoctorsAvailDateTime.executeTimeSlots(id,day)
    }

   suspend fun getDoctorsList(keyword:String, pageNum: Int):DoctorModelX?{
        return getDoctorList.execute(keyword,pageNum)
    }

    suspend fun getPrevBookings(id:String, pageNum: Int):BookingList?{
        return getBookings.executePrev(id,pageNum)
    }

    fun dropDatabase(){
        viewModelScope.launch {
            getBookings.dropDatabase()
        }
    }
}