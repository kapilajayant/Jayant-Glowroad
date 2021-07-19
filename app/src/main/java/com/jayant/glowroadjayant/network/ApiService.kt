package com.jayant.glowroadjayant.network

import com.jayant.glowroadjayant.models.ApiResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("rest/?method=flickr.photos.search&api_key=641c87bd78e54920b01e9a5d8bb726d7&format=json&nojsoncallback=1&extras=url_q&text=polo")
    fun getPhotos(@Query("page") page: String): Call<ApiResponse>

}