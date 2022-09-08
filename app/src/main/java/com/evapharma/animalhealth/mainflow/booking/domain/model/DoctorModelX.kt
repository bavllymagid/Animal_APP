package com.evapharma.animalhealth.mainflow.booking.domain.model

data class DoctorModelX(
    val doctors: List<DoctorModel>,
    val maxPage: Int,
    val pageNumber: Int
)