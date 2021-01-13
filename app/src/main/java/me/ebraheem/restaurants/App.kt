package me.ebraheem.restaurants

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import timber.log.Timber


@HiltAndroidApp
class App : Application(){

    init {
        instance = requireNotNull(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath(getString(R.string.app_font_regular))
                            .setFontAttrId(R.attr.fontPath)
                            .build()
                    )
                )
                .build()
        )

        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

    }


    companion object {

        @JvmStatic
        lateinit var instance: App

    }
}