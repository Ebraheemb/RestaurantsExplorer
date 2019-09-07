package me.ebraheem.restaurants.ui.search_city

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.ebraheem.restaurants.data.model.City
import me.ebraheem.restaurants.databinding.ItemSearchCityBinding

class SearchCityItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(city: City, onCityClickListener: OnCityClickListener) {
        binding.city = city
        itemView.setOnClickListener {
            onCityClickListener.onCityClick(city)
        }
    }

    companion object {

        private lateinit var binding: ItemSearchCityBinding

        fun createViewHolder(parent: ViewGroup): SearchCityItemVH {
            val layoutInflater = LayoutInflater.from(parent.context)
            binding = ItemSearchCityBinding.inflate(layoutInflater, parent, false)
            return SearchCityItemVH(binding.root)
        }
    }
}