package com.jayant.glowroadjayant.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jayant.glowroadjayant.models.ApiResponse
import com.jayant.glowroadjayant.models.PhotoModel
import com.jayant.glowroadjayant.network.ApiUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosViewModel : ViewModel() {

    private val TAG = PhotosViewModel::class.java.simpleName
    internal var photosList : MutableLiveData<ArrayList<PhotoModel>> = MutableLiveData()

    init {
        getDataFromApi("1")
    }

    fun getPhotos() : MutableLiveData<ArrayList<PhotoModel>>{
        return photosList
    }

    private fun getDataFromApi(page: String){

        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "getPhotos: $page")

            ApiUtils.getApiService()?.getPhotos(page)?.enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    Log.d(TAG, "onResponse: ${response.code()}")
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

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })
        }
    }

}