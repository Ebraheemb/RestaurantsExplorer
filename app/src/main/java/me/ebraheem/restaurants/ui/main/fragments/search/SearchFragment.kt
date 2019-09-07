package me.ebraheem.restaurants.ui.main.fragments.search


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.fragment_search.*
import me.ebraheem.restaurants.GlideApp

import me.ebraheem.restaurants.R
import me.ebraheem.restaurants.common.Constants
import me.ebraheem.restaurants.data.model.City
import me.ebraheem.restaurants.data.preferences.AppSharedPreferences
import me.ebraheem.restaurants.ui.main.fragments.HomeBaseFragment

class SearchFragment : HomeBaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GlideApp.with(this).load(R.drawable.rest_feature)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(8, 2)))
            .into(imageView)

        setButtonsListeners()
    }

    private fun setButtonsListeners() {

        searchCardView.setOnClickListener {
            searchForCity()
        }

        londonCityCardView.setOnClickListener {
            selectCity(Constants.LONDON_CITY_ID)
        }
    }

    override fun onNewCitySelected(city: City) {
        super.onNewCitySelected(city)
        if(city!= AppSharedPreferences.NONE_SELECTED_CITY)
        mainActivityDelegate!!.onNewCitySelected(city)
    }
    private fun selectCity(cityId: Int) {
        //todo
    }


    companion object {

        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

}
