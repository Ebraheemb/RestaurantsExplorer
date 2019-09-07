package me.ebraheem.restaurants.ui.main.fragments.favorites


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import me.ebraheem.restaurants.R
import me.ebraheem.restaurants.ui.base.BaseFragment


class FavoritesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }


    companion object {
        @JvmStatic
        fun newInstance() = FavoritesFragment()

    }
}
