package com.mvvm_clean.about_canada.features.movies

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvm_clean.about_canada.R
import com.mvvm_clean.about_canada.core.extension.inflate
import com.mvvm_clean.about_canada.core.extension.loadFromUrl
import kotlinx.android.synthetic.main.about_canada_item.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class MoviesAdapter
@Inject constructor() : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    internal var collection: List<Row> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    /* Uncomment when implementing click listener
      internal var clickListener: (MovieView, Navigator.Extras) -> Unit = { _, _ -> }
  */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(parent.inflate(R.layout.about_canada_item))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
            viewHolder.bind(collection[position])

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieView: Row) {

            if (movieView.isEmpty()){
                itemView.visibility = View.GONE
                itemView.setLayoutParams(RecyclerView.LayoutParams(0, 0))
            }else{
                itemView.visibility = View.VISIBLE
                itemView.setLayoutParams(RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
            }

            var imageUrl = movieView.imageHrefNotNull
            imageUrl = imageUrl.replace("http", "https")
            itemView.iv_list_item_logo.loadFromUrl(imageUrl)

            itemView.tv_list_item_description.text = movieView.descriptionNotNull

            itemView.tv_list_item_heading.text = movieView.titleNotNull
        }
    }
}
