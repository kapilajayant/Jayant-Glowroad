package com.jayant.glowroadjayant.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotosModel(
    var page: Int,
    var pages: Int,
    var perpage: Int,
    var total: Int,
    val photo: ArrayList<PhotoModel>
) : Parcelable
