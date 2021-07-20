package com.jayant.glowroadjayant.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoModel(
    var title: String,
    var url_q: String,
): Parcelable
