package ebraheem.tmdb.movies.utils

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.restaurant_info.*
import me.zhanghai.android.materialratingbar.MaterialRatingBar


object BindingAdapters {

    @BindingAdapter("imageUrl")
    @JvmStatic fun bindImage(imageView: ImageView, imagePath: String?) {
        if (imagePath == null)
            return

        Glide.with(imageView)
            .load(imagePath)
            .into(imageView)
    }


    @BindingAdapter("rating")
    @JvmStatic fun bindImage(ratingBar: MaterialRatingBar, rating: String?) {
        if (rating == null)
            return

        ratingBar.rating = rating.toFloat()
    }

    @BindingAdapter("rating")
    @JvmStatic fun bindImage(ratingBar: MaterialRatingBar, rating: Double) {

        ratingBar.rating = rating.toFloat()
    }

    @SuppressLint("SetTextI18n")
    @BindingAdapter("foodie_level")
    @JvmStatic fun bindFoodieLevel(textView: TextView, level: Long) {

        textView.text = "Level $level"
    }



    @BindingAdapter("visibleGone")
    @JvmStatic fun showHide(view: View, show: Boolean) {
        if (show)
            view.visibility = View.VISIBLE
        else
            view.visibility = View.GONE
    }



}