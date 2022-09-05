package com.evapharma.animalhealth.authflow.data.remote.models

import android.os.Parcelable
import com.evapharma.animalhealth.authflow.domain.model.CustomerModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CustomerItem (
    val UserName: String,
    val PhoneNumber: String,
    val Password: String
): Parcelable

