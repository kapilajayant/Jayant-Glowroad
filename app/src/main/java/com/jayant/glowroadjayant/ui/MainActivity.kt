package com.jayant.glowroadjayant.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jayant.glowroadjayant.adapters.PhotosAdapter
import com.jayant.glowroadjayant.databinding.ActivityMainBinding
import com.jayant.glowroadjayant.models.PhotoModel
import com.jayant.glowroadjayant.utils.NetworkHelper.isNetworkAvailable
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
        binding.swipe.isRefreshing = true
        binding.swipe.setOnRefreshListener {
            binding.swipe.isRefreshing = true
            if (isNetworkAvailable(this)) {
                observeData()
            } else {
                binding.rvImages.visibility = View.GONE
                binding.shimmerFrameLayout.stopShimmerAnimation()
                binding.shimmerFrameLayout.visibility = View.GONE
                binding.ivInternet.visibility = View.VISIBLE
                binding.swipe.isRefreshing = false
                Toast.makeText(this, "Connect to internet", Toast.LENGTH_SHORT).show()
            }
        }
        binding.rvImages.layoutManager = layoutManager
        if (isNetworkAvailable(this)) {
            observeData()
        } else {
            binding.rvImages.visibility = View.GONE
            binding.shimmerFrameLayout.stopShimmerAnimation()
            binding.shimmerFrameLayout.visibility = View.GONE
            binding.ivInternet.visibility = View.VISIBLE
            binding.swipe.isRefreshing = false
            Toast.makeText(this, "Connect to internet", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeData() {
        // observing the photos list and updating ui accordingly
        photosViewModel.getPhotos("1").observe(this, Observer {

            binding.swipe.isRefreshing = false
            binding.shimmerFrameLayout.stopShimmerAnimation()
            binding.shimmerFrameLayout.visibility = View.GONE

            Log.d(TAG, it.toString())

            binding.rvImages.adapter = PhotosAdapter(this@MainActivity, it)

            binding.rvImages.visibility = View.VISIBLE

        })

    }


}