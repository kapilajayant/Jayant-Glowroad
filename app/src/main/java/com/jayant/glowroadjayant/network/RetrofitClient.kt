package com.jayant.glowroadjayant.network;

import com.google.gson.GsonBuilder
import com.jayant.glowroadjayant.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitClient {

    companion object{

        private var okHttpClient = OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .followRedirects(true)
                .build()

        private const val baseUrl = Constants.BASE_URL

        fun getClient(): Retrofit? {

            val gson = GsonBuilder().setLenient().create()

            return Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
        }


    }

}