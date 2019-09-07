package me.ebraheem.restaurants.ui.main.fragments.home.best_rated

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_restaurant_rounded_corners_card.view.*
import me.ebraheem.restaurants.R
import me.ebraheem.restaurants.data.model.Restaurant
import me.ebraheem.restaurants.data.model.RestaurantWrapper
import me.ebraheem.restaurants.helpers.loadImage
import me.ebraheem.restaurants.ui.base.BaseViewHolder
import me.ebraheem.restaurants.ui.restaurant.RestaurantsActivity

class BestRestaurantsAdapter(var list: List<RestaurantWrapper>) : RecyclerView.Adapter<RestaurantItemVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantItemVH = RestaurantItemVH.createViewHolder(parent)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RestaurantItemVH, position: Int) {
        holder.bind(list[position])
    }
}


class RestaurantItemVH(itemView: View) : BaseViewHolder(itemView) {
    private var restaurant: Restaurant? = null
    val featureImageView = itemView.featureImageView
    val resNameTextView = itemView.resNameTextView
    val ratingBar = itemView.ratingBar
    val votesNumberTextView = itemView.votesNumberTextView

    init {
        itemView.setOnClickListener {view ->
            restaurant!!.id?.let { restaurantId ->
                RestaurantsActivity.getNewIntent(itemView.context as AppCompatActivity, restaurantId).also {
                    view.context.startActivity(it)
                }
            }
        }
    }

    fun bind(restaurant: RestaurantWrapper) {
        this.restaurant = restaurant.restaurant
        restaurant.let {
            featureImageView.loadImage(it.restaurant!!.featuredImage)
            resNameTextView.text = it.restaurant!!.name
            ratingBar.rating = it.restaurant!!.userRating!!.aggregateRating!!.toFloat()
            votesNumberTextView.text = itemView.context.getString(R.string.votes_number).format(it.restaurant!!.userRating!!.votes!!.toInt())
        }

    }

    companion object {

        private const val LAYOUT_RES = R.layout.item_restaurant_rounded_corners_card

        fun createViewHolder(parent: ViewGroup): RestaurantItemVH {
            return RestaurantItemVH(LayoutInflater.from(parent.context).inflate(LAYOUT_RES, parent, false))
        }
    }
}