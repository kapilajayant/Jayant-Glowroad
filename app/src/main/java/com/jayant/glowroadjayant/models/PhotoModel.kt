package com.jayant.glowroadjayant.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoModel(
    var id: String,
    var owner: String,
    var secret: String,
    var server: String,
    var farm: Int,
    var title: String,
    var ispublic: Int,
    var isfriend: Int,
    var isfamily: Int,
    var url_q: String,
    var height_q: Int,
    var width_q: Int
) : Parcelable
