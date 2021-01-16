package com.mvvm_clean.about_canada.features.canada_facts.view.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvm_clean.about_canada.R
import com.mvvm_clean.about_canada.core.extension.inflate
import com.mvvm_clean.about_canada.core.extension.loadFromUrl
import com.mvvm_clean.about_canada.features.canada_facts.view.FactRowViewModel
import kotlinx.android.synthetic.main.canada_fact_list_items.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class CanadaFactListAdapter
@Inject constructor() : RecyclerView.Adapter<CanadaFactListAdapter.ViewHolder>() {

    internal var collection: List<FactRowViewModel> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(parent.inflate(R.layout.canada_fact_list_items))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
            viewHolder.bind(collection[position])

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(factRowViewModel: FactRowViewModel) {

            hideEmptyItems(factRowViewModel)

            var imageUrl = factRowViewModel.imageHrefNotNull
            imageUrl = imageUrl.replace("http", "https")
            setDataToItem(imageUrl, factRowViewModel)
        }

        private fun setDataToItem(
            imageUrl: String,
            factRowViewModel: FactRowViewModel
        ) {
            itemView.iv_list_item_logo.loadFromUrl(imageUrl)
            itemView.tv_list_item_description.text = factRowViewModel.descriptionNotNull
            itemView.tv_list_item_heading.text = factRowViewModel.titleNotNull
        }

        private fun hideEmptyItems(factRowViewModel: FactRowViewModel) {
            if (factRowViewModel.isEmpty()) {
                itemView.visibility = View.GONE
                itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
            } else {
                itemView.visibility = View.VISIBLE
                itemView.layoutParams = RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }
    }
}
