package me.ebraheem.restaurants.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Ebraheem Bdarni on 5/18/2017.
 */

public class ScreenUtils {


    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static int pxToDp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

}
