package com.evapharma.animalhealth.authflow.data.remote.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CustomerItem (
    val UserName: String,
    val PhoneNumber: String,
    val Password: String
): Parcelable