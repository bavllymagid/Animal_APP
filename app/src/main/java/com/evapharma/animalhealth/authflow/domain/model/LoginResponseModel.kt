package com.evapharma.animalhealth.authflow.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponseModel(
    val isSuccess: Boolean,
    val token: String
) : Parcelable