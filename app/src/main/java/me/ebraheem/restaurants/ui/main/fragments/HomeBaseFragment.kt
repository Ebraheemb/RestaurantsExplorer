package me.ebraheem.restaurants.ui.main.fragments


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.CallSuper
import androidx.lifecycle.Observer
import me.ebraheem.restaurants.R
import me.ebraheem.restaurants.data.model.City
import me.ebraheem.restaurants.data.preferences.AppSharedPreferences
import me.ebraheem.restaurants.ui.base.BaseFragment
import me.ebraheem.restaurants.ui.main.MainActivityDelegate
import me.ebraheem.restaurants.ui.main.fragments.search.SearchFragment
import me.ebraheem.restaurants.ui.search_city.SearchCityActivity
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
open class HomeBaseFragment : BaseFragment() {


    protected var mainActivityDelegate: MainActivityDelegate? = null

    @Inject
    lateinit var prefs: AppSharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs.observableSelectedCity.observe(this,SelectedCityPreferenceObserver())
    }

    inner class SelectedCityPreferenceObserver : Observer<City> {
        override fun onChanged(t: City) {
            onNewCitySelected(t)
        }

    }

    @CallSuper
    protected open fun onNewCitySelected(city: City) {
        //do something here
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RQ_SELECT_CITY && resultCode == Activity.RESULT_OK) {
            // TODO -> to be deleted, no need for it, now we use the observable preference.
            var city = data!!.getParcelableExtra<City>(SearchCityActivity.EXTRA_CITY)
            //mainActivityDelegate!!.onCitySelected(city)

        }
    }



    protected fun searchForCity() {
        SearchCityActivity.getNewIntent(context as Activity).also {
            startActivityForResult(it, RQ_SELECT_CITY)
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivityDelegate)
            mainActivityDelegate = context
    }

    override fun onDetach() {
        super.onDetach()
        mainActivityDelegate = null
    }


    companion object {

        const val RQ_SELECT_CITY = 0x12

    }

}
