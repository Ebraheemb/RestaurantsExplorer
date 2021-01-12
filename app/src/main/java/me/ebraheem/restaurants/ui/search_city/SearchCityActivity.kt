package me.ebraheem.restaurants.ui.search_city

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jakewharton.rxbinding3.widget.textChanges
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import me.ebraheem.restaurants.R
import me.ebraheem.restaurants.data.model.City
import me.ebraheem.restaurants.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_search_city.*
import me.ebraheem.restaurants.data.preferences.AppSharedPreferences
import me.ebraheem.restaurants.helpers.GridCitiesItemDecoration
import me.ebraheem.restaurants.helpers.hide
import me.ebraheem.restaurants.helpers.plus
import me.ebraheem.restaurants.helpers.show
import me.ebraheem.restaurants.ui.search_city.city_details_dialog.CityDetailsDialogFragment
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@AndroidEntryPoint
class SearchCityActivity : BaseActivity(), OnCityClickListener, CityDetailsDialogFragment.Listener {

    @Inject
    lateinit var prefs: AppSharedPreferences

    private val viewModel: SearchCityViewModel by viewModels()
    private lateinit var citiesAdapter: SearchCityAdapter
    private var currentSearchQuery: String? = null
    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_city)
        setFinishOnTouchOutside(true)

        viewModel.citiesListLiveData.observe(this, CityListObserver())
        viewModel.loadingDataLiveData.observe(this, LoadingObserver())

        initSearch()
        initRecyclerView()

        swipeRefreshLayout.setOnRefreshListener {
            search()
        }
    }

    private fun initRecyclerView() {
        citiesAdapter = SearchCityAdapter(this)
        var decoration = GridCitiesItemDecoration(resources.getDimensionPixelSize(R.dimen.grid_search_city_item_margin))
        recyclerView.apply {
            adapter = citiesAdapter
            addItemDecoration(decoration)
        }

    }

    private fun initSearch() {

        compositeDisposable + searchEditText.textChanges()
            .skipInitialValue()
            .debounce(500, TimeUnit.MILLISECONDS)
            .map { it.toString() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.isNotEmpty()) {
                    currentSearchQuery = it
                    search()
                }
            }

    }

    private fun search() {
        //after the first search stay hide this
        startSearchTextView.hide()

        currentSearchQuery?.let {
            viewModel.searchFor(it)
        }
    }


    override fun onCityClick(city: City) {

//
//        var dialogFragment = CityDetailsDialogFragment.newInstance(city)
//        dialogFragment.show(supportFragmentManager, "CityDetailsDialogFragment")

        //1. save in preferences
        prefs.putSelectedCity(city)

        //. finish with sending city parcel object
        finishWithResultOk(city)
    }


    private fun finishWithResultOk(city: City) {

        var resultIntent = Intent().putExtra(EXTRA_CITY, city)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    override fun onAccept(city: City) {

    }


    /**
     * CityListObserver
     */
    inner class CityListObserver : Observer<List<City>> {

        override fun onChanged(cities: List<City>?) {
            citiesAdapter.submitList(cities)
            if (cities!!.isEmpty())
                noResusltTextView.show()
            else
                noResusltTextView.hide()
        }

    }

    /**
     * LoadingObserver
     */
    inner class LoadingObserver : Observer<Boolean> {

        override fun onChanged(loading: Boolean) {
            swipeRefreshLayout.isRefreshing = loading
        }

    }

    companion object {

        const val EXTRA_CITY = "extra_city"
        fun getNewIntent(activity: Activity): Intent {
            return Intent(activity, SearchCityActivity::class.java)
        }
    }

}