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
import com.jayant.glowroadjayant.models.PhotoModel
import com.jayant.glowroadjayant.viewmodels.PhotosViewModel

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
        // observing the photos list and updating ui accordingly
        photosViewModel.getPhotos("1").observe(this, Observer{

            binding.shimmerFrameLayout.stopShimmerAnimation()
            binding.shimmerFrameLayout.visibility = View.GONE

            Log.d(TAG, it.toString())

            binding.rvImages.adapter = PhotosAdapter(this@MainActivity, it)

            binding.rvImages.visibility = View.VISIBLE

        })

    }


}