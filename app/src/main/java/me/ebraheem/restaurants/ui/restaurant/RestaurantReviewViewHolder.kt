package me.ebraheem.restaurants.ui.restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import me.ebraheem.restaurants.R
import me.ebraheem.restaurants.data.model.ReviewWrapper
import me.ebraheem.restaurants.databinding.ItemRestaurantReviewBinding
import me.ebraheem.restaurants.ui.base.BaseViewHolder


class RestaurantReviewViewHolder(var binding: ItemRestaurantReviewBinding) : BaseViewHolder(binding.root) {

    fun bind(reviewWrapper : ReviewWrapper) {

       // binding.review = reviewWrapper.review[0]
    }

    companion object {
        private const val LAYOUT_RES = R.layout.item_restaurant_review
        fun createViewHolder(parent: ViewGroup): RestaurantReviewViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            var binding: ItemRestaurantReviewBinding = ItemRestaurantReviewBinding.inflate(layoutInflater,parent,false)
            return RestaurantReviewViewHolder(binding)
        }
    }
}