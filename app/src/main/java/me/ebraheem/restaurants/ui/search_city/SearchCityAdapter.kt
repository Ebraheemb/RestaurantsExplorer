package me.ebraheem.restaurants.ui.search_city

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import me.ebraheem.restaurants.data.model.City

class SearchCityAdapter constructor(var onCityClickListener: OnCityClickListener) : ListAdapter<City, SearchCityItemVH>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchCityItemVH {
        return SearchCityItemVH.createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: SearchCityItemVH, position: Int) {
        holder.bind(currentList[position],onCityClickListener)
    }

    companion object {

        internal var diffCallback: DiffUtil.ItemCallback<City> = object : DiffUtil.ItemCallback<City>() {
            override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
