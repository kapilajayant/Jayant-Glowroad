package com.jayant.glowroadjayant.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jayant.glowroadjayant.adapters.PhotosAdapter
import com.jayant.glowroadjayant.databinding.ActivityMainBinding
import com.jayant.glowroadjayant.models.ApiResponse
import com.jayant.glowroadjayant.models.PhotoModel
import com.jayant.glowroadjayant.network.ApiUtils
import com.jayant.glowroadjayant.viewmodels.PhotosViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName
    lateinit var binding: ActivityMainBinding

    private lateinit var photosList: ArrayList<PhotoModel>

    private var adapter: PhotosAdapter? = null

    private var layoutManager = LinearLayoutManager(this)
    private lateinit var photosViewModel: PhotosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        photosViewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)


        binding.shimmerFrameLayout.startShimmerAnimation()

        photosList = ArrayList()

        setupUI()

    }

    private fun setupUI() {

        binding.rvImages.layoutManager = layoutManager
        observeData()
    }

    private fun observeData(){
        photosViewModel.getPhotos().observe(this, Observer{

            binding.shimmerFrameLayout.stopShimmerAnimation()
            binding.shimmerFrameLayout.visibility = View.GONE

            Log.d(TAG, it.toString())

            binding.rvImages.adapter = PhotosAdapter(this@MainActivity, it)

            binding.rvImages.visibility = View.VISIBLE

        })
    }

    private fun getPhotos(page: String) {
        GlobalScope.launch(Dispatchers.IO) {

            ApiUtils.getApiService()?.getPhotos(page)?.enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    binding.shimmerFrameLayout.stopShimmerAnimation()
                    binding.shimmerFrameLayout.visibility = View.GONE
                    if (response.isSuccessful) {
                        try {
                            Log.d(TAG, "onResponse: ${response.body()}")
                            Log.d(TAG, "onResponse: ${response.body()?.photos?.photo?.size}")

                            response.body()?.photos?.photo?.forEach { photoModel ->
                                photosList.add(photoModel)
                            }

                            Log.d(TAG, "onResponse: $photosList")

                            adapter = PhotosAdapter(this@MainActivity, photosList)
                            binding.rvImages.adapter = adapter
                            binding.rvImages.visibility = View.VISIBLE
                            adapter?.notifyDataSetChanged()

                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                    binding.shimmerFrameLayout.stopShimmerAnimation()
                    binding.shimmerFrameLayout.visibility = View.GONE

                }

            })
        }
    }

}