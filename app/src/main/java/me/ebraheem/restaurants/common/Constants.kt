package me.ebraheem.restaurants.common

import me.ebraheem.restaurants.BuildConfig

object Constants {

    val LONDON_CITY_ID: Int = 59
    val PREFERENCES_FILE_NAME: String  = "my_app_prefs"
    //    val APP_DATABASE_NAME: String = "_app_db"
    val ZAMATO_API_KEY: String = BuildConfig.ZAMATO_API_KEY
    val API_BASE_URL: String ="https://developers.zomato.com"
    val OKHTTP_CACHE_DIR_NAME: String ="http.cache.directory"
    val OKHTTP_CACHE_DIR_SIZE: Long = 1024 * 1024 * 5

}