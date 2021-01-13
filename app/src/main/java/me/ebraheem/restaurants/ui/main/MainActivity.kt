package me.ebraheem.restaurants.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import me.ebraheem.restaurants.R
import me.ebraheem.restaurants.data.model.City
import me.ebraheem.restaurants.data.model.LocationDetails
import me.ebraheem.restaurants.data.network.ENTITY_TYPE_CITY
import me.ebraheem.restaurants.data.preferences.AppSharedPreferences
import me.ebraheem.restaurants.data.preferences.AppSharedPreferences.Companion.NONE_SELECTED_CITY
import me.ebraheem.restaurants.ui.base.BaseActivity
import me.ebraheem.restaurants.ui.main.fragments.favorites.FavoritesFragment
import me.ebraheem.restaurants.ui.main.fragments.home.HomeFragment
import me.ebraheem.restaurants.ui.main.fragments.search.SearchFragment
import me.ebraheem.restaurants.ui.main.fragments.user.UserFragment
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity(), MainActivityDelegate {

    @Inject
    lateinit var prefs: AppSharedPreferences
    private val viewModel: MainActivityViewModel by viewModels()
    private var selectedCity: City? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectedCity = prefs.getSelectedCity()
        setupBottomNavigation()
        setFirstFragment()
    }



    override fun getLocationDetails(): MutableLiveData<LocationDetails> =
        viewModel.getLocationDetails("${selectedCity!!.id}", ENTITY_TYPE_CITY)


    override fun onNewCitySelected(city: City) {
        selectedCity = city
        setFragment(HomeFragment.newInstance())
        setBottomNavigationHomeMode()
    }

    override fun onChangeSelectedCity(city: City) {
        viewModel.loadCity("${selectedCity!!.id}", ENTITY_TYPE_CITY)
    }


    private fun setFirstFragment() {

        if (selectedCity == NONE_SELECTED_CITY) {
            setFragment(SearchFragment.newInstance())
            //toolbar.hide()
        } else {
            setFragment(HomeFragment.newInstance())
            // toolbar.hide()
        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    private fun setBottomNavigationSearchMode() {
        bottom_navigation.menu.findItem(R.id.search)!!.isVisible = true
        bottom_navigation.menu.findItem(R.id.home)!!.isVisible = false
    }

    private fun setBottomNavigationHomeMode() {
        bottom_navigation.menu.findItem(R.id.search)!!.isVisible = false
        bottom_navigation.menu.findItem(R.id.home)!!.isVisible = true
    }

    private fun setupBottomNavigation() {
        if (selectedCity == NONE_SELECTED_CITY) {
            setBottomNavigationSearchMode()
        } else {
            setBottomNavigationHomeMode()
        }
        bottom_navigation.setOnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.home -> {
                    setFragment(HomeFragment.newInstance())
                    true
                }
                R.id.search -> {
                    setFragment(SearchFragment.newInstance())
                    true
                }
                R.id.favorite -> {
                    setFragment(FavoritesFragment.newInstance())
                    true
                }
                R.id.user -> {
                    setFragment(UserFragment.newInstance())
                    true
                }
                else -> {
                    false
                }
            }
        }
    }


}
