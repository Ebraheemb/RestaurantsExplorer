package me.ebraheem.restaurants.data


import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import me.ebraheem.restaurants.App
import me.ebraheem.restaurants.data.model.*
import me.ebraheem.restaurants.data.network.Routes
import java.util.concurrent.TimeUnit

class AppDataRepository(
    private var app: App,
    private var apiService: Routes
) : DataRepository {


    /**
     *  Cache cities search queries for one session
     */
    private var searchCityMap: MutableMap<String, List<City>> = mutableMapOf()

    /**
     * Cache [Restaurant]s for one session
     */
    private var restaurantsMap: MutableMap<String, Restaurant> = mutableMapOf()

    override fun restaurant(resId: String): Observable<Restaurant> {
        restaurantsMap[resId]?.let {
            return Observable.just(it)
                .delay(400, TimeUnit.MILLISECONDS) // Just to simulate network request time
        }
        return apiService.restaurant(resId)
            .subscribeOn(Schedulers.io())
            .map {
                restaurantsMap[resId] = it
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
    }


    override fun locationDetails(
        entityId: String,
        entityType: String
    ): Observable<LocationDetails> {

        return apiService.locationDetails(entityId, entityType)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun searchLocation(query: String): Observable<LocationGeneralResponse<Location>> =
        apiService.locations(query)

    /**
     * We save the query in a map to make a kind of memory cache for the next same query requests
     * We save every time a list of [City] mapped to the query as a key
     * this list we return immediately in next search by the same query.
     */
    override fun searchCity(query: String): Observable<List<City>> {
        searchCityMap[query]?.let {
            return Observable.just(it)
        }
        return apiService.cities(query)
            .subscribeOn(Schedulers.io())
            .flatMap {
                Observable.just(it.locationSuggestions)
            }
            .map {
                //                if (it.isNotEmpty()) // Actually no need to check this
                searchCityMap[query] = it
                it
            }
            .observeOn(AndroidSchedulers.mainThread())

    }


    override fun loadHomePage(entityId: String, entityType: String): Observable<HomePageData> {
        var ldSource = apiService.locationDetails(entityId, entityType)
            //Save the restaurants into the cache hashMap
            .map {
                it.bestRatedRestaurant!!.forEach { wrapper ->

                    wrapper.restaurant?.let { res ->
                        res.id?.let { id ->
                            restaurantsMap[id] = res
                        }
                    }
                }
                it
            }.doOnError {
                Log.d("error",it.toString())
            }

        var searchSource = apiService.search(entityId, entityType)
            .flatMap {
                Observable.just(it.restaurants ?: listOf())
            }
            .flatMap { it ->
                var newList: MutableList<Restaurant> = mutableListOf()
                it.forEach {
                    it.restaurant?.let { rest -> newList.add(rest) }
                }


                Observable.just(newList.toList())
            }
            //Save the restaurants into the cache hashMap
            .map {
                it.forEach { restaurant ->
                    restaurant.id?.let { id ->
                        restaurantsMap[id] = restaurant
                    }
                }

                it
            }.doOnError {
                Log.d("error",it.toString())
            }


        return Observable.zip(ldSource, searchSource, ZipBiFunction())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * Zip the results from [LocationDetails] and [SearchResponse]
     * and return new object that represent the [HomePageData]
     */
    inner class ZipBiFunction : BiFunction<LocationDetails, List<Restaurant>, HomePageData> {
        override fun apply(
            locationDetails: LocationDetails,
            restaurantsList: List<Restaurant>
        ): HomePageData {
            Log.d("Repository","res ${restaurantsList.size}")
            return HomePageData(locationDetails, restaurantsList)
        }

    }


}