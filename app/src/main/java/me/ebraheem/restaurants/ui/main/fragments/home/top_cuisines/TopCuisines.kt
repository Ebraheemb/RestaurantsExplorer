package me.ebraheem.restaurants.ui.main.fragments.home.top_cuisines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_top_cuisines.view.*
import me.ebraheem.restaurants.R
import me.ebraheem.restaurants.ui.base.BaseViewHolder



class TopCuisinesAdapter(var list: List<String>) : RecyclerView.Adapter<TopCuisinesItemVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopCuisinesItemVH =
        TopCuisinesItemVH.createViewHolder(parent)


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TopCuisinesItemVH, position: Int) {
        holder.bind(list[position])
    }
}



class TopCuisinesItemVH(itemView: View) : BaseViewHolder(itemView) {

    val cuisinesTextView: TextView = itemView.cuisinesTextView

    fun bind(cuisine :  String) {
        cuisinesTextView.text =  cuisine
    }

    companion object {
        private const val LAYOUT_RES = R.layout.item_top_cuisines
        fun createViewHolder(parent: ViewGroup): TopCuisinesItemVH {
            return TopCuisinesItemVH(LayoutInflater.from(parent.context).inflate(LAYOUT_RES, parent, false))
        }
    }
}


