package me.ebraheem.restaurants.utils;

import android.util.Log;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeSearchBehavior extends CoordinatorLayout.Behavior<View> {

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }


    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {

//        AppBarLayout appBarLayout = (AppBarLayout) dependency;
//
//        int range = appBarLayout.getTotalScrollRange();
//        float dy = range - appBarLayout.getY();

//        Log.d(TAG, "onDependentViewChanged: range = "+range);
//        Log.d(TAG, "onDependentViewChanged: dy = "+dy);
        Log.d(TAG, "onDependentViewChanged: here ");



        return true;
    }
}
