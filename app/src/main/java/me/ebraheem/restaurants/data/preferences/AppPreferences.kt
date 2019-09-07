package me.ebraheem.restaurants.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import me.ebraheem.restaurants.App
import me.ebraheem.restaurants.common.Constants
import me.ebraheem.restaurants.data.model.City
import me.ebraheem.restaurants.data.preferences.AppSharedPreferences.Companion.NONE_SELECTED_CITY

class AppPreferences(var app: App, var gson: Gson) :
    AppSharedPreferences {

    private val prefs : Lazy<SharedPreferences> = lazy {
        app.getSharedPreferences(
            Constants.PREFERENCES_FILE_NAME, Context.MODE_PRIVATE
        ).apply {
            registerOnSharedPreferenceChangeListener(changeListener)
        }
    }

    private val observableCity = MutableLiveData<City>()

    override val observableSelectedCity: MutableLiveData<City>
        get() {
            observableCity.value = getSelectedCity()
            return observableCity
        }


    private val changeListener = SharedPreferences.OnSharedPreferenceChangeListener{ _ ,key ->
        when(key){
            SELECTED_CITY -> observableCity.value = getSelectedCity()
        }

    }

    override fun getSelectedCity(): City {
        var json: String? =
            prefs.value.getString(SELECTED_CITY, null) ?: return NONE_SELECTED_CITY
        return gson.fromJson(json, City::class.java)
    }


    override fun putSelectedCity(city: City) {
        var json = gson.toJson(city)
        prefs.value.edit {
            putString(SELECTED_CITY, json)
        }
    }


    companion object {
        const val SELECTED_CITY: String = "selected_city"

    }

}
