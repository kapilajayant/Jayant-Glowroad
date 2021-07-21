package com.jayant.glowroadjayant.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jayant.glowroadjayant.models.ApiResponse
import com.jayant.glowroadjayant.models.PhotoModel
import com.jayant.glowroadjayant.network.ApiUtils
import com.jayant.glowroadjayant.repositories.PhotosRepository
import com.jayant.glowroadjayant.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosViewModel : ViewModel() {

    private val TAG = PhotosViewModel::class.java.simpleName
    private var photosList : MutableLiveData<ArrayList<PhotoModel>> = MutableLiveData()
    internal var status : MutableLiveData<String> = MutableLiveData()
    private val photosRepository = PhotosRepository()

    fun getPhotos(page: String) : MutableLiveData<ArrayList<PhotoModel>>{
        // checking if list if empty then getting list from repository
        // otherwise return the list
        // this is to handle screen orientation change or handling lifecycle changes of activity
        if(photosList.value == null){
            photosList = photosRepository.getDataFromApi(page)
        }
        return photosList
    }

}