package com.mvvm_clean.about_canada.features.canada_facts.presentation.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvm_clean.about_canada.R
import com.mvvm_clean.about_canada.core.domain.extension.*
import com.mvvm_clean.about_canada.features.canada_facts.presentation.models.FactRowModel
import kotlinx.android.synthetic.main.canada_fact_list_items.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

// Adapter responsible to show item of canada fact list
class CanadaFactListAdapter
@Inject constructor() : RecyclerView.Adapter<CanadaFactListAdapter.ViewHolder>() {

    internal var collection: List<FactRowModel> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    // Override Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(parent.inflate(R.layout.canada_fact_list_items))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
            viewHolder.bind(collection[position])

    override fun getItemCount() = collection.size
    //---

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(factRowModel: FactRowModel) {

            hideEmptyItems(factRowModel)

            var imageUrl = factRowModel.imageHrefNotNull
            imageUrl = imageUrl?.replace(itemView.getString(R.string.http), itemView.getString(R.string.https))
            imageUrl?.let { setDataToItem(it, factRowModel) }
        }

        private fun setDataToItem(
            imageUrl: String,
            factRowModel: FactRowModel
        ) {
            itemView.iv_list_item_logo.loadFromUrl(imageUrl)
            itemView.tv_list_item_description.text = factRowModel.descriptionNotNull
            itemView.tv_list_item_heading.text = factRowModel.titleNotNull
        }

        private fun hideEmptyItems(factRowModel: FactRowModel) {
            if (factRowModel.isEmpty()) {
                itemView.gone()
                itemView.setWithAndHeight(0, 0)
            } else {
                itemView.visible()
                itemView.setWithAndHeight(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)

            }
        }
    }
}

