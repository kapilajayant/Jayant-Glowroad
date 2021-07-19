package com.jayant.glowroadjayant.models

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
)
