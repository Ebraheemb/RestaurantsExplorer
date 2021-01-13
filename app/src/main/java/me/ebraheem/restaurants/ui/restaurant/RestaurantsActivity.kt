package me.ebraheem.restaurants.ui.restaurant

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_restaurants.*
import kotlinx.android.synthetic.main.layout_shimmer_loading_page.*
import kotlinx.android.synthetic.main.activity_restaurants.res_info_shimmer_layout as resInfoShimmerLayout
import kotlinx.android.synthetic.main.restaurant_info.*
import me.ebraheem.restaurants.R
import me.ebraheem.restaurants.data.model.Restaurant
import me.ebraheem.restaurants.data.result.Result
import me.ebraheem.restaurants.helpers.hide
import me.ebraheem.restaurants.helpers.loadImage
import me.ebraheem.restaurants.helpers.show
import me.ebraheem.restaurants.ui.base.BaseActivity
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class RestaurantsActivity : BaseActivity() {

    private val viewModel: RestaurantActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurants)

        initToolbar()

        intent.getStringExtra(EXTRA_RESTAURANT_ID)?.let { resId ->
            viewModel.loadRestaurant(resId).observe(this,RestaurantLiveDataObserver())
        }

        backButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initToolbar() {
        // initialize action bar
        setSupportActionBar(toolbar)
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
        //collapsing tool bar settings
        collapsing_toolbar?.setExpandedTitleColor(Color.WHITE)//transparent
        collapsing_toolbar?.setCollapsedTitleTextColor(Color.WHITE)
    }

    inner class RestaurantLiveDataObserver : Observer<Result<RestaurantActivityData>> {
        override fun onChanged(result: Result<RestaurantActivityData>) {
            when(result){
                is Result.Success -> {
                    loading(false)
                    bindView(result.data)
                }
                is Result.Loading -> loading(true)
                is Result.Error -> Timber.e(result.exception)
            }
        }
    }

    private fun bindView(restaurantData: RestaurantActivityData) {
        resInfo.show()
        coverImage.loadImage(restaurantData.restaurant.featuredImage)
        restaurantNameTextView.text = restaurantData.restaurant.name
        materialRatingBar.rating = restaurantData.restaurant.userRating!!.aggregateRating!!.toFloat()
        votesNumberTextView.text =
            getString(R.string.votes_number).format(restaurantData.restaurant.userRating!!.votes!!.toInt())

        initializeRecycler(restaurantData)
    }

    private fun initializeRecycler(restaurantData: RestaurantActivityData) {
        val dataList: MutableList<RestaurantInfoAdapterData> = mutableListOf()

        //1. prepare photos section
        restaurantData.restaurant.photos?.let {
            dataList.add(ResPhotos(it))
        }
        //2. prepare reviews section
        dataList.add(ResSectionTitle(R.string.restaurant_reviews_section_title))
        dataList.addAll(restaurantData.reviews.map { review->
            ResReview(review)
        })

        //3. TODO add more sections ...

        recyclerView.apply {
            adapter = RestaurantInfoAdapter(dataList)
        }
    }

    fun loading(loading : Boolean){
        if (loading) {
            shimmer_layout.startShimmer()
            shimmer_Scroll.show()
            resInfoShimmerLayout.startShimmerAnimation()
        } else {
            shimmer_Scroll.hide()
            shimmer_layout.stopShimmer()
            resInfoShimmerLayout.stopShimmerAnimation()
        }
    }

    companion object {

        private const val EXTRA_RESTAURANT_ID = "extra_restaurant_id"

        fun getNewIntent(activity: AppCompatActivity, restaurantId: String): Intent =
            Intent(activity, RestaurantsActivity::class.java).putExtra(
                EXTRA_RESTAURANT_ID,
                restaurantId
            )

        fun getNewIntent(context: Context, restaurantId: String): Intent =
            Intent(context, RestaurantsActivity::class.java)
                .putExtra(EXTRA_RESTAURANT_ID, restaurantId)
    }
}
