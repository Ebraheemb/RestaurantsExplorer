package me.ebraheem.restaurants.ui.restaurant

import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import me.ebraheem.restaurants.R
import me.ebraheem.restaurants.data.model.PhotoWrapper
import me.ebraheem.restaurants.data.model.Review
import me.ebraheem.restaurants.data.model.ReviewWrapper
import me.ebraheem.restaurants.ui.base.BaseViewHolder
import me.ebraheem.restaurants.ui.base.shared.BasicViewHolderHorizontalRecyclerAndBoldTitle
import me.ebraheem.restaurants.ui.base.shared.OnlyTitleSectionViewHolder
import me.ebraheem.restaurants.ui.main.fragments.home.top_cuisines.TopCuisinesAdapter


const val TYPE_PHOTOS = 1
const val TYPE_CUISINES = 2
const val TYPE_SECTION_TITLE = 3
const val TYPE_REVIEW = 4

sealed class RestaurantInfoAdapterData(var type : Int)
data class ResPhotos(var photos: List<PhotoWrapper>) : RestaurantInfoAdapterData(TYPE_PHOTOS)
data class ResCuisines(var topCuisines: List<String>) : RestaurantInfoAdapterData(TYPE_CUISINES)
data class ResSectionTitle(@StringRes var stringId : Int) : RestaurantInfoAdapterData(TYPE_SECTION_TITLE)
data class ResReview(var review : Review) : RestaurantInfoAdapterData(TYPE_REVIEW)


class RestaurantInfoAdapter(var dataList: MutableList<RestaurantInfoAdapterData>) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        when (viewType) {
            TYPE_PHOTOS -> BasicViewHolderHorizontalRecyclerAndBoldTitle.createViewHolder(parent)
            TYPE_CUISINES -> BasicViewHolderHorizontalRecyclerAndBoldTitle.createViewHolder(parent)
            TYPE_SECTION_TITLE -> OnlyTitleSectionViewHolder.createViewHolder(parent)
            TYPE_REVIEW -> RestaurantReviewViewHolder.createViewHolder(parent)
            else -> BaseViewHolder(View(parent.context))
        }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        var data = dataList[position]

        if ( data is ResCuisines){
            (holder as BasicViewHolderHorizontalRecyclerAndBoldTitle).bind(R.string.restaurant_cuisines,TopCuisinesAdapter(data.topCuisines))
        }

        if ( data is ResPhotos){
            (holder as BasicViewHolderHorizontalRecyclerAndBoldTitle).bind(R.string.restaurant_photos,RestuarantPhotosAdapter(data.photos))
        }

        if ( data is ResSectionTitle){
            (holder as OnlyTitleSectionViewHolder).bind(data.stringId)
        }

        if ( data is ResReview){
            (holder as RestaurantReviewViewHolder).bind(data.review)
        }

    }

    override fun getItemViewType(position: Int): Int = dataList[position].type

}



