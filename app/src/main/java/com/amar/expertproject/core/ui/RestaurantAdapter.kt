package com.amar.expertproject.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amar.expertproject.R
import com.amar.expertproject.core.domain.model.Restaurant
import com.amar.expertproject.databinding.ItemListRestaurantBinding
import com.bumptech.glide.Glide

class RestaurantAdapter : RecyclerView.Adapter<RestaurantAdapter.ListViewHolder>() {

    private var listData = ArrayList<Restaurant>()
    var onItemClick: ((Restaurant) -> Unit)? = null

    fun setData(newListData: List<Restaurant>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_restaurant, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListRestaurantBinding.bind(itemView)
        fun bind(restaurant: Restaurant) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("https://restaurant-api.dicoding.dev/images/large/${restaurant.pictureId}")
                    .into(ivItemImage)
                tvItemTitle.text = restaurant.name
                tvItemSubtitle.text = restaurant.description
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}