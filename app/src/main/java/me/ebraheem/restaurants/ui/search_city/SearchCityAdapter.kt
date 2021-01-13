package me.ebraheem.restaurants.ui.search_city

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
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

interface OnCityClickListener {
    fun onCityClick(city: City)
}