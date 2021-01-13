package me.ebraheem.restaurants.ui.main.fragments.home.restaurant_marker_bottom_sheet_dialog

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_restaurant_marker_dialog.*
import kotlinx.android.synthetic.main.fragment_restaurant_marker_dialog.view.*
import kotlinx.android.synthetic.main.item_bottom_sheet_restaurant_info.*
import me.ebraheem.restaurants.R
import me.ebraheem.restaurants.data.model.Restaurant
import me.ebraheem.restaurants.data.result.Result
import me.ebraheem.restaurants.helpers.hide
import me.ebraheem.restaurants.helpers.loadImage
import me.ebraheem.restaurants.helpers.show
import timber.log.Timber


const val ARG_RESTAURANT_ID = "restaurant_id"

@AndroidEntryPoint
class RestaurantMarkerDialogFragment : BottomSheetDialogFragment() {

    private var restaurantId: String? = null

    val viewModel : RestaurantMarkerBottomSheetDialogViewModel by viewModels()

    private var mRestaurantMarkerInfoDialogListener: RestaurantMarkerInfoDialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant_marker_dialog, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restaurantId = requireArguments().getString(ARG_RESTAURANT_ID) ?: kotlin.run {
            Timber.e("Restaurant id is null")
            return
        }

        viewModel.loadRestaurant(restaurantId!!).observe(this, { result ->
          when(result){
              is Result.Success -> {
                  bindView(result.data)
                  shimmer_layout.hide()
                  shimmer_layout.stopShimmer()
              }
              is Result.Error -> {
                  shimmer_layout.hide()
                  shimmer_layout.stopShimmer()
              }
              is Result.Loading -> {
                  shimmer_layout.show()
                  shimmer_layout.startShimmer()
              }
          }
      })
    }

    private fun bindView(restaurant: Restaurant) {
        infoLayout.show()
        imageView.loadImage(restaurant.featuredImage)
        resNameTextView.text = restaurant.name
        ratingBar.rating = restaurant.userRating!!.aggregateRating!!.toFloat()
        votesNumberTextView.text =
            getString(R.string.votes_number).format(restaurant.userRating!!.votes!!.toInt())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.moreInfoButton.setOnClickListener {
            mRestaurantMarkerInfoDialogListener!!.onRestaurantMoreInfoClicked(restaurantId?:"")
            dismiss()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val parent = parentFragment
        if (parent != null) {
            mRestaurantMarkerInfoDialogListener = parent as RestaurantMarkerInfoDialogListener
        } else {
            mRestaurantMarkerInfoDialogListener = context as RestaurantMarkerInfoDialogListener
        }
    }

    override fun onDetach() {
        mRestaurantMarkerInfoDialogListener = null
        super.onDetach()
    }

    interface RestaurantMarkerInfoDialogListener {
        fun onRestaurantMoreInfoClicked(restId: String)
    }


    companion object {

        fun newInstance(restaurantID: String): RestaurantMarkerDialogFragment =
            RestaurantMarkerDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_RESTAURANT_ID, restaurantID)
                }
            }

    }
}
