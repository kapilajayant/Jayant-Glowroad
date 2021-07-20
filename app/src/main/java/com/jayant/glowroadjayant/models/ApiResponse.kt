package com.jayant.glowroadjayant.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApiResponse(
    var photos: PhotosModel,
    var stat: String
) : Parcelable
