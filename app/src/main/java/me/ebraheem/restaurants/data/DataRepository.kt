package me.ebraheem.restaurants.data

import io.reactivex.Observable
import me.ebraheem.restaurants.data.model.*

interface DataRepository {

    fun searchCity(query: String): Observable<List<City>>

    fun searchLocation(query: String) : Observable<LocationGeneralResponse<Location>>

    fun restaurant(resId: String) : Observable<Restaurant>

    fun locationDetails(entityId: String, entityType: String): Observable<LocationDetails>

    fun loadHomePage(entityId: String, entityType: String): Observable<HomePageData>

}
