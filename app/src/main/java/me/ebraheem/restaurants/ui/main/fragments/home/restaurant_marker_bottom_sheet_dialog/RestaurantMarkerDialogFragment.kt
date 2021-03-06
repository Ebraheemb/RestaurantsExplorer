package me.ebraheem.restaurants.ui.main.fragments.home.restaurant_marker_bottom_sheet_dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_restaurant_marker_dialog.*
import kotlinx.android.synthetic.main.fragment_restaurant_marker_dialog.view.*
import kotlinx.android.synthetic.main.item_bottom_sheet_restaurant_info.*
import kotlinx.android.synthetic.main.item_bottom_sheet_restaurant_info.votesNumberTextView
import me.ebraheem.restaurants.R
import me.ebraheem.restaurants.data.model.Restaurant
import me.ebraheem.restaurants.helpers.hide
import me.ebraheem.restaurants.helpers.loadImage
import me.ebraheem.restaurants.helpers.show
import me.ebraheem.restaurants.viewmodels.ViewModelProviderFactory
import me.ebraheem.restaurants.widget.DaggerBottomSheetDialogFragment
import javax.inject.Inject


const val ARG_RESTAURANT_ID = "restaurant_id"

class RestaurantMarkerDialogFragment : DaggerBottomSheetDialogFragment() {

    private var restaurantId: String? = null
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    private var mRestaurantMarkerInfoDialogListener: RestaurantMarkerInfoDialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant_marker_dialog, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restaurantId = arguments!!.getString(ARG_RESTAURANT_ID)
        var viewModel = ViewModelProviders.of(this,providerFactory).get(RestaurantMarkerBottomSheetDialogViewModel::class.java)
        viewModel.restaurantLiveData.observe(this, Observer {
            bindView(it)
        })
        viewModel.loadingDataLiveData.observe(this, Observer {
            if(it){
                shimmer_layout.show()
                shimmer_layout.startShimmer()
            }else{
                shimmer_layout.hide()
                shimmer_layout.stopShimmer()
            }

        })
        restaurantId?.let { viewModel.loadRestaurant(it) }
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
