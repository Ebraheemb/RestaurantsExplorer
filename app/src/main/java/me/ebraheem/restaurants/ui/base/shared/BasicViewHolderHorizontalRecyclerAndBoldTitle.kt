package me.ebraheem.restaurants.ui.base.shared

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import me.ebraheem.restaurants.R
import me.ebraheem.restaurants.ui.base.BaseViewHolder

class BasicViewHolderHorizontalRecyclerAndBoldTitle(itemView: View) : BaseViewHolder(itemView) {

    val recyclerView: RecyclerView by lazy {
        itemView.findViewById<RecyclerView>(R.id.recyclerView)
    }

    val sectionTitleTextView: TextView by lazy {
        itemView.findViewById<TextView>(R.id.sectionTitleTextView)
    }


    fun bind(sectionTitle: String, adapter: Adapter<out BaseViewHolder>) {
        sectionTitleTextView.text = sectionTitle
        recyclerView.adapter = adapter
    }
    fun bind(@StringRes sectionTitle: Int, adapter: Adapter<out BaseViewHolder>) {
        sectionTitleTextView.setText(sectionTitle)
        recyclerView.adapter = adapter
    }


    companion object {

        private const val LAYOUT_RES =
            R.layout.item_basic_view_holder_with_horizontal_recycler_and_bold_title

        fun createViewHolder(parent: ViewGroup): BasicViewHolderHorizontalRecyclerAndBoldTitle {
            return BasicViewHolderHorizontalRecyclerAndBoldTitle(
                LayoutInflater.from(parent.context).inflate(
                    LAYOUT_RES,
                    parent,
                    false
                )
            )
        }
    }
}
