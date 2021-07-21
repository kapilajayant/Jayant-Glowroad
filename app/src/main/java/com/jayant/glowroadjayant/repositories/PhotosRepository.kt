package com.jayant.glowroadjayant.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jayant.glowroadjayant.models.ApiResponse
import com.jayant.glowroadjayant.models.PhotoModel
import com.jayant.glowroadjayant.network.ApiUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class PhotosRepository {

    internal var page = 1

    private val TAG = PhotosRepository::class.java.simpleName

    private var photosList = MutableLiveData<ArrayList<PhotoModel>>()

    // method to make api call and return the list
    internal fun getDataFromApi(page: Int): MutableLiveData<ArrayList<PhotoModel>>{
        GlobalScope.launch(Dispatchers.IO) {
            Log.d(TAG, "getPhotos page: $page")
            val response = ApiUtils.getApiService()?.getPhotos(page)?.execute()
            Log.d(TAG, "getDataFromApi: $response?.raw()?.request()?.url()")
            if (response != null) {
                if (response.isSuccessful) {
                    try {
                        Log.d(TAG, "onResponse: ${response.body()}")
                        Log.d(TAG, "onResponse: ${response.body()?.photos?.photo?.size}")

                        photosList.postValue(response.body()?.photos?.photo)

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

        }

        Log.d(TAG, "getPhotos photosList: ${photosList.value}")
        return photosList
    }
}