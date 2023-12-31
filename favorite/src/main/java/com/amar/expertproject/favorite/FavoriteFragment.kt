package com.amar.expertproject.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.amar.expertproject.core.ui.RestaurantAdapter
import com.amar.expertproject.detail.DetailRestaurantActivity
import com.amar.expertproject.di.FavoriteDependencies
import com.amar.expertproject.favorite.databinding.FragmentFavoriteBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding

    @Inject
    lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        initDaggerFavorite()
        super.onCreate(savedInstanceState)

    }

    private fun initDaggerFavorite() {
        val coreDependencies = EntryPointAccessors.fromApplication(
            requireContext().applicationContext,
            FavoriteDependencies::class.java
        )
        DaggerFavoriteComponent.builder()
            .context(requireContext())
            .dependencies(coreDependencies)
            .build().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val restaurantAdapter = RestaurantAdapter()
            restaurantAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailRestaurantActivity::class.java)
                intent.putExtra(DetailRestaurantActivity.EXTRA_DATA, selectedData.restaurantId)
                startActivity(intent)
            }

            favoriteViewModel.favoriteRestaurant.observe(viewLifecycleOwner) { dataRestaurant ->
                binding.viewEmpty.root.visibility =
                    if (dataRestaurant.isNotEmpty()) View.GONE else View.VISIBLE
                restaurantAdapter.setData(dataRestaurant)
            }

            with(binding.rvRestaurant) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = restaurantAdapter
            }
        }
    }

}
