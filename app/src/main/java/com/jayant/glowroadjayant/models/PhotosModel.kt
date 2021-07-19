package com.jayant.glowroadjayant.models

data class PhotosModel(
    var page: Int,
    var pages: Int,
    var perpage: Int,
    var total: Int,
    val photo: ArrayList<PhotoModel>
)
