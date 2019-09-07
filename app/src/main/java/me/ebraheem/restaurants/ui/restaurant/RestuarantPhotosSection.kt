package me.ebraheem.restaurants.ui.restaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_restaurant_photo.view.*
import me.ebraheem.restaurants.R
import me.ebraheem.restaurants.data.model.PhotoWrapper
import me.ebraheem.restaurants.helpers.loadImage
import me.ebraheem.restaurants.ui.base.BaseViewHolder


class RestuarantPhotosAdapter(var list: List<PhotoWrapper>) : RecyclerView.Adapter<RestaurantPhotoItemVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantPhotoItemVH =
        RestaurantPhotoItemVH.createViewHolder(parent)


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RestaurantPhotoItemVH, position: Int) {
        holder.bind(list[position])
    }
}



class RestaurantPhotoItemVH(itemView: View) : BaseViewHolder(itemView) {

    val imageView: ImageView = itemView.imageView

    fun bind(photo : PhotoWrapper) {
        photo.photo?.let {
            imageView.loadImage(it.thumbUrl)
        }

    }

    companion object {
        private const val LAYOUT_RES = R.layout.item_restaurant_photo
        fun createViewHolder(parent: ViewGroup): RestaurantPhotoItemVH {
            return RestaurantPhotoItemVH(LayoutInflater.from(parent.context).inflate(LAYOUT_RES, parent, false))
        }
    }
}

