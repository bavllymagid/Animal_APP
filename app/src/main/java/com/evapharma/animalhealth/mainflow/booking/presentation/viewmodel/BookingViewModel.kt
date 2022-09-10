package com.evapharma.animalhealth.mainflow.booking.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.evapharma.animalhealth.mainflow.booking.domain.model.*
import com.evapharma.animalhealth.mainflow.booking.domain.usecases.BookAnAppointment
import com.evapharma.animalhealth.mainflow.booking.domain.usecases.GetDoctorList
import com.evapharma.animalhealth.mainflow.booking.domain.usecases.GetDoctorsAvailDateTime
import com.evapharma.animalhealth.mainflow.booking.domain.usecases.GetMyBookings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val bookAnAppointment: BookAnAppointment,
    private val getMyBookings: GetMyBookings,
    private val getDoctorsAvailDateTime: GetDoctorsAvailDateTime,
    private val getDoctorList: GetDoctorList
) : ViewModel() {

    fun bookAppointment(token:String, appointment: AppointmentModel){
        viewModelScope.launch {
            bookAnAppointment.execute(token ,appointment)
        }
    }

    fun getBookings(id:String, pageNum:Int):LiveData<List<BookingModel>>{
        return liveData {
            emit(getMyBookings.execute(id, pageNum))
        }
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
}