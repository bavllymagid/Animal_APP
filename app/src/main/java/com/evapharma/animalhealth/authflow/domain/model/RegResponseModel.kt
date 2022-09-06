package com.evapharma.animalhealth.authflow.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegResponseModel(
    val isSuccess: Boolean,
    val message: String
):Parcelable