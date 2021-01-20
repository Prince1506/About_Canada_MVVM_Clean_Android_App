package com.mvvm_clean.about_canada.features.canada_facts.presentation.ui.fragments

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvm_clean.about_canada.R
import com.mvvm_clean.about_canada.core.base.BaseFragment
import com.mvvm_clean.about_canada.core.domain.exception.Failure
import com.mvvm_clean.about_canada.core.domain.exception.Failure.NetworkConnection
import com.mvvm_clean.about_canada.core.domain.exception.Failure.ServerError
import com.mvvm_clean.about_canada.core.domain.extension.*
import com.mvvm_clean.about_canada.core.presentation.navigation.Navigator
import com.mvvm_clean.about_canada.features.canada_facts.data.CanadaFactsFailure
import com.mvvm_clean.about_canada.features.canada_facts.presentation.models.CanadaFactsModel
import com.mvvm_clean.about_canada.features.canada_facts.presentation.models.CanadaFactsViewModel
import com.mvvm_clean.about_canada.features.canada_facts.presentation.ui.activities.CanadaFactListActivity
import com.mvvm_clean.about_canada.features.canada_facts.presentation.ui.adapters.CanadaFactListAdapter
import kotlinx.android.synthetic.main.fragment_canada_facts.*
import javax.inject.Inject

// Fragment responsible to show fact list
class CanadaFactListFragment : BaseFragment() {

    private val BUNDLE_RECYCLER_LAYOUT = "classname.recycler.layout"
    private lateinit var mCanadaFactsViewModel: CanadaFactsViewModel

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var canadaFactListAdapter: CanadaFactListAdapter


    // Override Methods
    override fun layoutId() = R.layout.fragment_canada_facts
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        mCanadaFactsViewModel = viewModel(viewModelFactory) {
            observe(canadaFacts, ::renderCanadaFactsList)
            failure(failure, ::handleFailure)
        }
        // retain this fragment when activity is re-initialized
        setRetainInstance(true);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        showProgress()
        loadCanadaFactsList()
    }

    /**
     * This is a method for Fragment.
     * You can do the same in onCreate or onRestoreInstanceState
     */
    override fun onViewStateRestored(@Nullable savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            val savedRecyclerLayoutState =
                savedInstanceState.getParcelable<Parcelable>(BUNDLE_RECYCLER_LAYOUT)
            rv_canadaFactList.getLayoutManager()?.onRestoreInstanceState(savedRecyclerLayoutState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(
            BUNDLE_RECYCLER_LAYOUT,
            rv_canadaFactList.getLayoutManager()?.onSaveInstanceState()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rv_canadaFactList.adapter = null;
    }
    //---

    private fun initializeView() {
        rv_canadaFactList.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )

        rv_canadaFactList.adapter = canadaFactListAdapter

        srl_canada_fact_pullToRefresh.setOnRefreshListener {
            loadCanadaFactsList()
        }

    }

    private fun loadCanadaFactsList() {
        mCanadaFactsViewModel.loadCanadaFacts()
    }

    private fun renderCanadaFactsList(canadaFactsModel: CanadaFactsModel?) {

        canadaFactsModel?.title?.let { (activity as CanadaFactListActivity).setActionTitle(it) }
        canadaFactListAdapter.collection = canadaFactsModel?.factRowEntity!!
        srl_canada_fact_pullToRefresh.isRefreshing = false
        emptyView.gone()
        tv_fact_list_api_message.gone()
        rv_canadaFactList.visible()
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is ServerError -> renderFailure(R.string.failure_server_error)
            is CanadaFactsFailure.ListNotAvailable -> renderFailure(R.string.failure_canada_fact_list_unavailable)
            else -> renderFailure(R.string.failure_server_error)
        }
        srl_canada_fact_pullToRefresh.isRefreshing = false
    }

    private fun renderFailure(@StringRes message: Int) {
        rv_canadaFactList.gone()
        (activity as CanadaFactListActivity).setActionTitle("")
        emptyView.visible()
        tv_fact_list_api_message.visible()
        tv_fact_list_api_message.text = getString(message)
        hideProgress()
        notifyWithAction(message)
    }
}
