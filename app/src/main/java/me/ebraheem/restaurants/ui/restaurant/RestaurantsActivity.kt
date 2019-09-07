package me.ebraheem.restaurants.ui.restaurant

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_restaurants.*
import kotlinx.android.synthetic.main.layout_shimmer_loading_page.*
import kotlinx.android.synthetic.main.activity_restaurants.res_info_shimmer_layout as resInfoShimmerLayout
import kotlinx.android.synthetic.main.restaurant_info.*
import me.ebraheem.restaurants.R
import me.ebraheem.restaurants.data.model.Restaurant
import me.ebraheem.restaurants.helpers.hide
import me.ebraheem.restaurants.helpers.loadImage
import me.ebraheem.restaurants.helpers.show
import me.ebraheem.restaurants.ui.base.BaseActivity
import me.ebraheem.restaurants.viewmodels.ViewModelProviderFactory
import javax.inject.Inject


class RestaurantsActivity : BaseActivity() {


    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    private lateinit var viewModel: RestaurantActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurants)

        initToolbar()

        viewModel = ViewModelProviders.of(this, providerFactory).get(RestaurantActivityViewModel::class.java)
        viewModel.loadingDataLiveData.observe(this, LoadingLiveDataObserver())
        viewModel.restaurantLiveData.observe(this, RestaurantLiveDataObserver())


        var resId = intent!!.getStringExtra(EXTRA_RESTAURANT_ID)
        viewModel.loadRestaurant(resId)


        backButton.setOnClickListener {
            onBackPressed()
        }


    }


    private fun initToolbar() {
        // initialize action bar
        setSupportActionBar(toolbar)
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = ""
        //collapsing tool bar settings
        collapsing_toolbar!!.setExpandedTitleColor(Color.WHITE)//transparent
        collapsing_toolbar!!.setCollapsedTitleTextColor(Color.WHITE)

    }

    inner class RestaurantLiveDataObserver : Observer<Restaurant> {

        override fun onChanged(restaurant: Restaurant) {

            bindView(restaurant)

        }

    }

    private fun bindView(restaurant: Restaurant) {
        resInfo.show()
        coverImage.loadImage(restaurant.featuredImage)
        restaurantNameTextView.text = restaurant.name
        materialRatingBar.rating = restaurant.userRating!!.aggregateRating!!.toFloat()
        votesNumberTextView.text = getString(R.string.votes_number).format(restaurant.userRating!!.votes!!.toInt())

        initializeRecycler(restaurant)
    }

    private fun initializeRecycler(restaurant: Restaurant) {
        var dataList: MutableList<RestaurantInfoAdapterData> = mutableListOf()

        //1. prepare photos section
        restaurant.photos?.let {
            dataList.add(ResPhotos(it))
        }
        //2. prepare reviews section
        restaurant.allReviews?.let {
            dataList.add(ResSectionTitle(R.string.restaurant_reviews_section_title))
            it.reviews?.forEach { dataList.add(ResReview(it)) } }

        //3. TODO add more sections ...

        recyclerView.apply {
            adapter = RestaurantInfoAdapter(dataList)
        }

    }


    inner class LoadingLiveDataObserver : Observer<Boolean> {

        override fun onChanged(loading: Boolean) {
            if(loading){
                shimmer_layout.startShimmer()
                shimmer_Scroll.show()
                resInfoShimmerLayout.startShimmerAnimation()
            }
            else{
                shimmer_Scroll.hide()
                shimmer_layout.stopShimmer()
                resInfoShimmerLayout.stopShimmerAnimation()


            }
        }

    }


    companion object {

        private const val EXTRA_RESTAURANT_ID = "extra_restaurant_id"

        fun getNewIntent(activity: AppCompatActivity, restaurantId: String): Intent =
            Intent(activity, RestaurantsActivity::class.java).putExtra(
                EXTRA_RESTAURANT_ID,
                restaurantId
            )

    }
}
