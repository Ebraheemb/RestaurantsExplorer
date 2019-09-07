package me.ebraheem.restaurants.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import de.hdodenhof.circleimageview.CircleImageView;
import me.ebraheem.restaurants.GlideApp;
import me.ebraheem.restaurants.R;

public class CustomMarkerMakerUtils {

    public interface MakerListener {
        void onBitmapReady(Bitmap bitmap);
    }


    public static void getMarker(Context context,final String url, final MakerListener listener) {

        final View view = getMakerView(context);
        final CircleImageView img = (CircleImageView) view.findViewById(R.id.img);
        int withHeightPexils = ScreenUtils.dpToPx(context,50);
        GlideApp.with(context).setDefaultRequestOptions(new RequestOptions().centerCropTransform()).asBitmap().load(url).into(new SimpleTarget<Bitmap>(withHeightPexils,withHeightPexils) {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {


                view.measure(View.MeasureSpec.makeMeasureSpec(withHeightPexils, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(withHeightPexils, View.MeasureSpec.EXACTLY));
                view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
                img.setImageBitmap(resource);
                view.buildDrawingCache();

                Bitmap returnedBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(returnedBitmap);
                canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
                Drawable drawable = view.getBackground();
                if (drawable != null)
                    drawable.draw(canvas);
                view.draw(canvas);
                listener.onBitmapReady(Bitmap.createScaledBitmap(returnedBitmap, withHeightPexils, withHeightPexils, false));

            }
        });




    }

    private static View getMakerView(Context context) {
        View customMarkerView = LayoutInflater.from(context).inflate(R.layout.restaurant_custom_marker, null);
        return customMarkerView;
    }
}