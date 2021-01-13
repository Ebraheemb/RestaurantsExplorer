package me.ebraheem.restaurants.data

import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.ebraheem.restaurants.App
import me.ebraheem.restaurants.data.model.*
import me.ebraheem.restaurants.data.network.Routes
import me.ebraheem.restaurants.data.result.Result
import java.lang.Exception

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

    override fun restaurantFlow(resId: String): Flow<Result<Restaurant>> = flow{

        emit(Result.Loading)
        emit(restaurant(resId))

    }

    override fun locationDetails(
        entityId: String,
        entityType: String
    ): Flow<Result<LocationDetails>> = flow {
            emit(Result.Loading)
            val response = apiService.locationDetails(entityId, entityType)
            if (response.isSuccessful && response.body()!=null){
                emit(Result.Success(response.body()!!))
            }else{
                emit(Result.Error(Exception("error")))
            }
    }

    override fun searchLocation(query: String): Observable<LocationGeneralResponse<Location>> =
        apiService.locations(query)

    /**
     * We save the query in a map to make a kind of memory cache for the next same query requests
     * We save every time a list of [City] mapped to the query as a key
     * this list we return immediately in next search by the same query.
     */
    override fun searchCity(query: String): Flow<Result<List<City>>> = flow{
        val cachedCity = searchCityMap[query]
        if (cachedCity!=null) {
            emit(Result.Success(cachedCity))
        } else {
            val searchCityResponse = apiService.cities(query)
            if (searchCityResponse.isSuccessful && searchCityResponse.body()!=null){
                val cities = searchCityResponse.body()!!.locationSuggestions
                emit(Result.Success(cities))
                //save to memory cache
                searchCityMap[query] = cities
            } else {
                emit(Result.Error(Exception("error")))
            }
        }
    }


    override fun loadHomePage(entityId: String, entityType: String): Flow<Result<HomePageData>> = flow {
        emit(Result.Loading)
        val locationDetails : LocationDetails
        val restaurantsList : List<Restaurant>

        val ldResponse = apiService.locationDetails(entityId, entityType)

        if (ldResponse.isSuccessful && ldResponse.body() != null){
            locationDetails = ldResponse.body()!!
            ldResponse.body()!!.bestRatedRestaurant.forEach { wrapper ->
                    wrapper.restaurant.let { res ->
                        restaurantsMap[res.id] = res
                    }
                }
        } else {
            emit(Result.Error(Exception("error")))
            return@flow
        }

        val searchResponse = apiService.search(entityId, entityType)

        if(searchResponse.isSuccessful && searchResponse.body()!=null){
            restaurantsList = (searchResponse.body()!!.restaurants ?: listOf()).map { restaurantWrapper ->
                    val restaurant = restaurantWrapper.restaurant
                    restaurantsMap[restaurant.id] = restaurant
                restaurant
            }
        } else {
            emit(Result.Error(Exception("error")))
            return@flow
        }

        emit(Result.Success(HomePageData(locationDetails, restaurantsList)))

    }

    override suspend fun restaurantReviews(resId: String): Result<List<Review>> {

        val restaurantReviewsResponse = apiService.restaurantReviews(resId)

        if (restaurantReviewsResponse.isSuccessful && restaurantReviewsResponse.body()!=null) {
            val reviews = restaurantReviewsResponse.body()!!.userReviews.filter {
                it.review != null
            }.map {
                it.review!!
            }
            return Result.Success(reviews)
        } else {
            return Result.Error(Exception("error"))
        }
    }

    override suspend fun restaurant(resId: String): Result<Restaurant> {
        val cachedRestaurant = restaurantsMap[resId]
        if(cachedRestaurant!=null){
            return Result.Success(cachedRestaurant)
        }

        val restaurantResponse = apiService.restaurant(resId)
        if (restaurantResponse.isSuccessful && restaurantResponse.body() != null){
            val restaurant = restaurantResponse.body()!!
            restaurantsMap[restaurant.id] = restaurant
            return Result.Success(restaurant)
        } else {
            return Result.Error(Exception("error"))
        }
    }
}

interface DataRepository {
    fun searchCity(query: String): Flow<Result<List<City>>>

    fun searchLocation(query: String) : Observable<LocationGeneralResponse<Location>>

    fun restaurantFlow(resId: String) : Flow<Result<Restaurant>>

    fun locationDetails(entityId: String, entityType: String): Flow<Result<LocationDetails>>

    fun loadHomePage(entityId: String, entityType: String): Flow<Result<HomePageData>>

    suspend fun restaurantReviews(resId: String): Result<List<Review>>

    suspend fun restaurant(resId: String) : Result<Restaurant>
}
