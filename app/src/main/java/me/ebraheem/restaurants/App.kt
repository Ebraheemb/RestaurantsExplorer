package me.ebraheem.restaurants

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import me.ebraheem.restaurants.di.AppComponent
import me.ebraheem.restaurants.di.AppModule
import me.ebraheem.restaurants.di.DaggerAppComponent


class App : DaggerApplication(){
    private lateinit var component: AppComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return component
    }


    override fun onCreate() {
        component = DaggerAppComponent.factory().create(AppModule(this))
        component.inject(this)
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

    }


    companion object {

        @JvmStatic
        var instance: App? = null
            private set

    }
}