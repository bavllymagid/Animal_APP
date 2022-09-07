package com.evapharma.animalhealth.authflow.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CustomerModel (
    val UserName: String,
    val PhoneNumber: String,
    val Password: String
): Parcelable
