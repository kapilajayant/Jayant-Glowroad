package com.jayant.glowroadjayant.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jayant.glowroadjayant.models.PhotoModel
import com.jayant.glowroadjayant.repositories.PhotosRepository

class PhotosViewModel : ViewModel() {

    private val TAG = PhotosViewModel::class.java.simpleName
    internal var photosList : MutableLiveData<ArrayList<PhotoModel>> = MutableLiveData()
    internal var status : MutableLiveData<String> = MutableLiveData()
    private val photosRepository = PhotosRepository()

    fun getPhotos(page: Int): MutableLiveData<ArrayList<PhotoModel>> {
        Log.d(TAG, "getPhotos page: $page")
        // checking if list if empty then getting list from repository
        // otherwise return the list
        // this is to handle screen orientation change or handling lifecycle changes of activity
        if(photosList.value == null){
            photosList = photosRepository.getDataFromApi(page)
        }
        return photosList
    }

}