package com.amar.expertproject.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.amar.expertproject.R
import com.amar.expertproject.databinding.ActivityDetailRestaurantBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailRestaurantActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailRestaurantViewModel: DetailRestaurantViewModel by viewModels()

    private lateinit var binding: ActivityDetailRestaurantBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailRestaurant = intent.getStringExtra(EXTRA_DATA) ?: ""
        showDetailRestaurant(detailRestaurant)

    }

    private fun showDetailRestaurant(idRestaurant: String) {
        detailRestaurantViewModel.getRestaurantDetail(idRestaurant).observe(this) { resources ->
            when (resources) {
                is com.amar.expertproject.core.data.Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is com.amar.expertproject.core.data.Resource.Success -> {
                    resources.data?.let { restaurant ->
                        binding.apply {
                            progressBar.visibility = View.GONE
                            tvDetailName.text = restaurant.name
                            tvDetailDescription.text = restaurant.description
                            tvDetailCity.text = restaurant.city
                            tvDetailRating.text = restaurant.rating.toString()
                        }
                        Glide.with(this)
                            .load("https://restaurant-api.dicoding.dev/images/large/${restaurant.pictureId}")
                            .into(binding.ivDetailImage)

                        var statusFavorite = restaurant.isFavorite
                        setStatusFavorite(statusFavorite)
                        binding.fab.setOnClickListener {
                            statusFavorite = !statusFavorite
                            detailRestaurantViewModel.setFavoriteRestaurant(
                                restaurant,
                                statusFavorite
                            )
                            setStatusFavorite(statusFavorite)
                        }
                    }
                }

                is com.amar.expertproject.core.data.Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.viewError.root.visibility = View.VISIBLE
                    binding.viewError.tvError.text =
                        resources.message ?: getString(R.string.something_wrong)
                }
            }
        }

        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_white
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite_white
                )
            )
        }
    }
}
