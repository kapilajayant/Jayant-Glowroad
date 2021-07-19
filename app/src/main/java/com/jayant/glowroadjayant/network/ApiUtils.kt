package com.jayant.glowroadjayant.network;

class ApiUtils {

    companion object {

        fun getApiService(): ApiService? {
            return RetrofitClient.getClient()?.create(ApiService::class.java)
        }

    }

}