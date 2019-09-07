package me.ebraheem.restaurants.ui.main.fragments.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.ebraheem.restaurants.R
import me.ebraheem.restaurants.data.model.Restaurant
import me.ebraheem.restaurants.data.model.RestaurantWrapper
import me.ebraheem.restaurants.ui.base.BaseViewHolder
import me.ebraheem.restaurants.ui.base.shared.BasicViewHolderHorizontalRecyclerAndBoldTitle
import me.ebraheem.restaurants.ui.main.fragments.home.best_rated.BestRestaurantsAdapter
import me.ebraheem.restaurants.ui.main.fragments.home.top_cuisines.TopCuisinesAdapter

 const val TYPE_TOP_CUISINES = 1
 const val TYPE_BEST_RATED_RESTAURANTS = 2
 const val TYPE_RESTAURANT = 3

sealed class HomeAdapterData(var type : Int)
data class TopCuisines(var topCuisines: List<String>) : HomeAdapterData(TYPE_TOP_CUISINES)
data class BestRatedRestaurants(var bestRatedRestaurants: List<RestaurantWrapper>) : HomeAdapterData(TYPE_BEST_RATED_RESTAURANTS)
data class RestaurantData(var restaurant : Restaurant) : HomeAdapterData(TYPE_RESTAURANT)


class HomeAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    var dataList: MutableList<HomeAdapterData> = mutableListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        when (viewType) {
            TYPE_BEST_RATED_RESTAURANTS -> BasicViewHolderHorizontalRecyclerAndBoldTitle.createViewHolder(parent)
            TYPE_TOP_CUISINES -> BasicViewHolderHorizontalRecyclerAndBoldTitle.createViewHolder(parent)
            TYPE_RESTAURANT -> RestaurantViewHolder.createViewHolder(parent)
            else -> BaseViewHolder(View(parent.context))
        }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (dataList[position].type== TYPE_TOP_CUISINES && holder is BasicViewHolderHorizontalRecyclerAndBoldTitle){
            var data = dataList[position] as TopCuisines
            holder.bind(R.string.top_cuisines,TopCuisinesAdapter(data.topCuisines))
        }

        if (dataList[position].type == TYPE_BEST_RATED_RESTAURANTS && holder is BasicViewHolderHorizontalRecyclerAndBoldTitle){
            var data =  dataList[position] as BestRatedRestaurants
            holder.bind(R.string.most_rated_restaurants, BestRestaurantsAdapter(data.bestRatedRestaurants))
        }

        if (dataList[position].type == TYPE_RESTAURANT && holder is RestaurantViewHolder){
            var data =  dataList[position] as RestaurantData
            holder.bind(data.restaurant)
        }
    }

    override fun getItemViewType(position: Int): Int = dataList[position].type

}