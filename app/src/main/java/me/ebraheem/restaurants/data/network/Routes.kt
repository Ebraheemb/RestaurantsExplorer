package me.ebraheem.restaurants.data.network

import io.reactivex.Observable
import me.ebraheem.restaurants.data.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


const val ENTITY_TYPE_CITY = "city"

interface Routes {

    @GET("/api/v2.1/cities")
    suspend fun cities(@Query("q") q: String): Response<LocationGeneralResponse<City>>

    @GET("/api/v2.1/locations")
    fun locations(@Query("q") q: String): Observable<LocationGeneralResponse<Location>>

    @GET("/api/v2.1/location_details")
    suspend fun locationDetails(
        @Query("entity_id") entityId: String,
        @Query("entity_type") entityType: String
    ): Response<LocationDetails>

    /**
     *  This route can be more filtered but for now I will use it in this way.
     *  Also it's limited to 20 restaurant per 1 search request, as described in this issue:
     *  https://stackoverflow.com/questions/42013020/zomato-api-always-returning-only-20-restaurants-in-search-by-location-response/42019514#42019514
     *
     *  ->
     *
     * Zomato API was made in order to ease the pagination process on search results. Hence, at a time you can fetch a maximum of 20 restaurants. Varying the API parameters 'start' and 'count' can get you upto 100 restaurants, for example, you can search from result number 1 to 20 and also result number 60 to 80 and likewise by varying API parameters multiple times to obtain all the subset of results that are provided by the search API. Here's an example:
     * https://developers.zomato.com/api/v2.1/search?entity_id=1&entity_type=city&start=0&count=20 gives you restaurants from 1 to 20
     * and
     * https://developers.zomato.com/api/v2.1/search?entity_id=1&entity_type=city&start=20&count=20 gives you restaurants from 21 to 40
     *
     */
    @GET("/api/v2.1/search")
    suspend fun search(
        @Query("entity_id") entityId: String,
        @Query("entity_type") entityType: String
    ): Response<SearchResponse>

    @GET("/api/v2.1/restaurant")
    suspend fun restaurant(
        @Query("res_id") resId: String
    ): Response<Restaurant>

    @GET("/api/v2.1/reviews")
    suspend fun restaurantReviews(
        @Query("res_id") resId: String
    ): Response<RestaurantReviewsResponse>
}
