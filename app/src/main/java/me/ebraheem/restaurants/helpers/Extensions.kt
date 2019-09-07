package me.ebraheem.restaurants.helpers

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

operator fun CompositeDisposable?.plus(disposable: Disposable?): CompositeDisposable? {
    disposable?.let {
        this?.add(it)
    }
    return this
}


inline fun View.hide() {
    this.visibility = View.GONE
}

inline fun View.show() {
    this.visibility = View.VISIBLE
}

inline fun ImageView.loadImage(url: String?) {
    Glide.with(this).load(url).into(this)
}


inline fun <A : Any?, B : Any?, R> let2(v1: A?, v2: B?, callback: (A, B) -> R): R? {
    return v1?.let { v1Verified ->
        v2?.let { v2Verified ->
            callback(v1Verified, v2Verified)
        }
    }
}

fun Fragment.shortToast(text :String){
    Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
}