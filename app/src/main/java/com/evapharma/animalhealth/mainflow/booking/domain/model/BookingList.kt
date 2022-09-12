package com.evapharma.animalhealth.mainflow.booking.domain.model

data class BookingList(
    val appointmentDetails: List<BookingModel>,
    val maxPage: Int,
    val pageNumber: Int
)