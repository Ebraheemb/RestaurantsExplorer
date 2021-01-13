package me.ebraheem.restaurants.data.model

import com.google.gson.annotations.SerializedName

data class Restaurant(

    @field:SerializedName("apikey")
    var apikey: String? = null,

    @field:SerializedName("id")
    var id: String = "",

    @field:SerializedName("name")
    var name: String = "",

    @field:SerializedName("url")
    var url: String? = null,

    @field:SerializedName("location")
    var location: Location? = null,

    @field:SerializedName("switch_to_order_menu")
    var switchToOrderMenu: Long = 0,

    @field:SerializedName("cuisines")
    var cuisines: String? = null,

    @field:SerializedName("timings")
    var timings: String? = null,

    @field:SerializedName("average_cost_for_two")
    var averageCostForTwo: Long = 0,

    @field:SerializedName("price_range")
    var priceRange: Long = 0,

    @field:SerializedName("currency")
    var currency: String? = null,

    @field:SerializedName("highlights")
    var highlights: List<String>? = null,

    @field:SerializedName("offers")
    var offers: List<Any>? = null,

    @field:SerializedName("opentable_support")
    var opentableSupport: Long = 0,

    @field:SerializedName("is_zomato_book_res")
    var isZomatoBookRes: Long = 0,

    @field:SerializedName("mezzo_provider")
    var mezzoProvider: String? = null,

    @field:SerializedName("is_book_form_web_view")
    var isBookFormWebView: Long = 0,

    @field:SerializedName("book_form_web_view_url")
    var bookFormWebViewUrl: String? = null,

    @field:SerializedName("book_again_url")
    var bookAgainUrl: String? = null,

    @field:SerializedName("thumb")
    var thumb: String? = null,

    @field:SerializedName("user_rating")
    var userRating: UserRating? = null,

    @field:SerializedName("all_reviews_count")
    var allReviewsCount: Long = 0,

    @field:SerializedName("photos_url")
    var photosUrl: String? = null,

    @field:SerializedName("photo_count")
    var photoCount: Long = 0,

    @field:SerializedName("photos")
    var photos: List<PhotoWrapper>? = null,

    @field:SerializedName("menu_url")
    var menuUrl: String? = null,

    @field:SerializedName("featured_image")
    var featuredImage: String? = null,

    @field:SerializedName("has_online_delivery")
    var hasOnlineDelivery: Long = 0,

    @field:SerializedName("is_delivering_now")
    var isDeliveringNow: Long = 0,

    @field:SerializedName("include_bogo_offers")
    var isIncludeBogoOffers: Boolean = false,

    @field:SerializedName("deeplink")
    var deeplink: String? = null,

    @field:SerializedName("is_table_reservation_supported")
    var isTableReservationSupported: Long = 0,

    @field:SerializedName("has_table_booking")
    var hasTableBooking: Long = 0,

    @field:SerializedName("events_url")
    var eventsUrl: String? = null,

    @field:SerializedName("phone_numbers")
    var phoneNumbers: String? = null,

    @field:SerializedName("all_reviews")
    var allReviews: AllReviews = AllReviews(),

    @field:SerializedName("establishment")
    var establishment: List<Any>? = null

)