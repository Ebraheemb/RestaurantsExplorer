package me.ebraheem.restaurants.ui.base.shared

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import kotlinx.android.synthetic.main.item_basic_view_holder_with_only_bold_title.view.*
import me.ebraheem.restaurants.R
import me.ebraheem.restaurants.ui.base.BaseViewHolder

class OnlyTitleSectionViewHolder(itemView: View) : BaseViewHolder(itemView) {

    val sectionTitleTextView: TextView = itemView.sectionTitleTextView


    fun bind(sectionTitle: String) {
        sectionTitleTextView.text = sectionTitle
    }
    fun bind(@StringRes sectionTitle: Int) {
        sectionTitleTextView.setText(sectionTitle)
    }


    companion object {

        private const val LAYOUT_RES =
            R.layout.item_basic_view_holder_with_only_bold_title

        fun createViewHolder(parent: ViewGroup): OnlyTitleSectionViewHolder {
            return OnlyTitleSectionViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    LAYOUT_RES,
                    parent,
                    false
                )
            )
        }
    }
}
