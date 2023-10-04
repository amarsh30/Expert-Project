package com.amar.expertproject.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.amar.expertproject.R
import com.amar.expertproject.core.domain.model.Restaurant
import com.amar.expertproject.core.ui.ViewModelFactory
import com.amar.expertproject.databinding.ActivityDetailRestaurantBinding
import com.bumptech.glide.Glide

class DetailRestaurantActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var detailRestaurantViewModel: DetailRestaurantViewModel
    private lateinit var binding: ActivityDetailRestaurantBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val factory = ViewModelFactory.getInstance(this)
        detailRestaurantViewModel = ViewModelProvider(this, factory)[DetailRestaurantViewModel::class.java]

        val detailRestaurant = intent.getParcelableExtra<Restaurant>(EXTRA_DATA)
        showDetailRestaurant(detailRestaurant)
    }

    private fun showDetailRestaurant(detailRestaurant: Restaurant?) {
        detailRestaurant?.let {
            supportActionBar?.title = detailRestaurant.name
            binding.content.tvDetailDescription.text = detailRestaurant.description
            Glide.with(this@DetailRestaurantActivity)
                .load(detailRestaurant.pictureId)
                .into(binding.ivDetailImage)

            var statusFavorite = detailRestaurant.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailRestaurantViewModel.setFavoriteRestaurant(detailRestaurant, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }
}
