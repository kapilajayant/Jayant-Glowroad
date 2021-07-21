package com.jayant.glowroadjayant.ui

import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.jayant.glowroadjayant.R
import com.jayant.glowroadjayant.databinding.ActivityDetailsBinding
import com.jayant.glowroadjayant.databinding.ActivityMainBinding
import com.jayant.glowroadjayant.utils.Constants
import com.jayant.glowroadjayant.utils.Constants.Companion.KEY_IMAGE_URL
import com.jayant.glowroadjayant.utils.Constants.Companion.KEY_TITLE

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        bundle?.let {

            val title = bundle.getString(KEY_TITLE, "")
            val imageUrl = bundle.getString(KEY_IMAGE_URL, "")
            val actionbar = supportActionBar
            if (actionbar != null) {
                actionbar.title = title
                actionbar.setDisplayHomeAsUpEnabled(true)
            }

            Glide.with(this)
                .load(imageUrl)
                .fitCenter()
                .into(binding.ivDetails)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            super.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}