package com.jayant.glowroadjayant.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jayant.glowroadjayant.R
import com.jayant.glowroadjayant.adapters.PhotosAdapter
import com.jayant.glowroadjayant.databinding.ActivityMainBinding
import com.jayant.glowroadjayant.models.PhotoModel
import com.jayant.glowroadjayant.utils.Constants
import com.jayant.glowroadjayant.utils.NetworkHelper.isNetworkAvailable
import com.jayant.glowroadjayant.utils.ProgressDialogHelper
import com.jayant.glowroadjayant.viewmodels.PhotosViewModel

class MainActivity : AppCompatActivity() {

    private var page = 1
    private val TAG = MainActivity::class.java.simpleName
    lateinit var binding: ActivityMainBinding

    private lateinit var photosList: ArrayList<PhotoModel>

    private var adapter: PhotosAdapter? = null

    private var layoutManager = LinearLayoutManager(this)
    private lateinit var photosViewModel: PhotosViewModel

    var isLoading = false

    var loader: ProgressDialogHelper? = null

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

        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window = this.window
                window.statusBarColor = resources.getColor(R.color.blackishGrey)
            }
        }

        loader = ProgressDialogHelper(this)

        // Initialising visibility of widgets

        // Setting swipe refresh listener

        binding.swipe.isRefreshing = true
        binding.swipe.setOnRefreshListener {
            binding.swipe.isRefreshing = true
            if (isNetworkAvailable(this)) {
                photosList.clear()
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
        }
        else
        {
            binding.rvImages.visibility = View.GONE
            binding.shimmerFrameLayout.stopShimmerAnimation()
            binding.shimmerFrameLayout.visibility = View.GONE
            binding.ivInternet.visibility = View.VISIBLE
            binding.swipe.isRefreshing = false
            Toast.makeText(this, "Connect to internet", Toast.LENGTH_SHORT).show()
        }

        // Initialising scroll listener

        binding.rvImages.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == photosList.size - 1) {
                        isLoading = true
                        binding.swipe.isRefreshing = true
//                        loader?.showProgressDialog()
                        Toast.makeText(this@MainActivity, "Loading...", Toast.LENGTH_SHORT).show()
                        page++
                        observeData()
                    }
                }
            }
        })

        // Setting up the adapter

        adapter = PhotosAdapter(this@MainActivity, photosList)
        binding.rvImages.adapter = adapter

    }

    private fun observeData() {
        isLoading = false
        Log.d(TAG, "observeData page: $page")
        // observing the photos list and updating ui accordingly

        photosViewModel.getPhotos(page).observe(this, Observer {

            binding.swipe.isRefreshing = false
            binding.shimmerFrameLayout.stopShimmerAnimation()
            binding.shimmerFrameLayout.visibility = View.GONE

            photosList.addAll(it)

//            loader?.hideProgressDialog()

            Log.d(TAG, photosList.toString())
            Log.d(TAG, photosList.size.toString())
            adapter?.notifyDataSetChanged()
            binding.rvImages.visibility = View.VISIBLE

        })

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_home, menu)

        val menu_info = menu.findItem(R.id.menu_info)
        menu_info.setOnMenuItemClickListener {

            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
            return@setOnMenuItemClickListener false
        }
        return true
    }


}