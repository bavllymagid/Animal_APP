package com.evapharma.animalhealth.authflow.domain.model

import android.os.Parcelable
import com.evapharma.animalhealth.authflow.data.remote.models.CustomerItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CustomerModel (
    val UserName: String,
    val PhoneNumber: String,
    val Password: String
): Parcelable

fun CustomerModel.toCustomerItem(): CustomerItem {
    return CustomerItem(
        UserName,
        PhoneNumber,
        Password
    )

}