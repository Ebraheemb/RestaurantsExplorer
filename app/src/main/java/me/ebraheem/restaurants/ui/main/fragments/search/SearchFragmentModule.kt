package me.ebraheem.restaurants.ui.main.fragments.search

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.ebraheem.restaurants.di.FragmentScope


@Module
abstract class SearchFragmentModule {

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun contributeSearchFragment2(): SearchFragment
}