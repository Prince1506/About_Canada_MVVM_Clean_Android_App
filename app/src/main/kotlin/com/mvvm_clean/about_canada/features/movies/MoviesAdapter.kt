package com.mvvm_clean.about_canada.features.movies

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvm_clean.about_canada.R
import com.mvvm_clean.about_canada.core.extension.inflate
import com.mvvm_clean.about_canada.core.extension.loadFromUrl
import com.mvvm_clean.about_canada.core.navigation.Navigator
import kotlinx.android.synthetic.main.row_movie.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class MoviesAdapter
@Inject constructor() : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    internal var collection: List<Row> by Delegates.observable(emptyList()) {
        _, _, _ -> notifyDataSetChanged()
    }

    internal var clickListener: (MovieView, Navigator.Extras) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(parent.inflate(R.layout.row_movie))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
            viewHolder.bind(collection[position], clickListener)

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieView: Row, clickListener: (MovieView, Navigator.Extras) -> Unit) {

            if (movieView.imageHref != null && movieView.imageHref.isNotEmpty())
                itemView.moviePoster.loadFromUrl(movieView.imageHref)
        }
    }
}
