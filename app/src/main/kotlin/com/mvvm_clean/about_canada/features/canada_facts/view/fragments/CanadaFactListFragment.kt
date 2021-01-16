package com.mvvm_clean.about_canada.features.canada_facts.view.fragments

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvm_clean.about_canada.R
import com.mvvm_clean.about_canada.core.exception.Failure
import com.mvvm_clean.about_canada.core.exception.Failure.NetworkConnection
import com.mvvm_clean.about_canada.core.exception.Failure.ServerError
import com.mvvm_clean.about_canada.core.extension.*
import com.mvvm_clean.about_canada.core.navigation.Navigator
import com.mvvm_clean.about_canada.core.platform.BaseFragment
import com.mvvm_clean.about_canada.features.canada_facts.data.CanadaFactsFailure
import com.mvvm_clean.about_canada.features.canada_facts.view.CanadaFactsView
import com.mvvm_clean.about_canada.features.canada_facts.view.CanadaFactsViewModel
import com.mvvm_clean.about_canada.features.canada_facts.view.activities.CanadaFactListActivity
import com.mvvm_clean.about_canada.features.canada_facts.view.adapters.CanadaFactListAdapter
import kotlinx.android.synthetic.main.fragment_canada_facts.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class CanadaFactListFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var canadaFactListAdapter: CanadaFactListAdapter

    private lateinit var mCanadaFactsViewModel: CanadaFactsViewModel

    override fun layoutId() = R.layout.fragment_canada_facts

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        mCanadaFactsViewModel = viewModel(viewModelFactory) {
            observe(canadaFacts, ::renderCanadaFactsList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        showProgress()
        loadCanadaFactsList()
    }


    private fun initializeView() {
        canadaFactList.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )

        canadaFactList.adapter = canadaFactListAdapter

        srl_canada_fact_pullToRefresh.setOnRefreshListener {
            loadCanadaFactsList()
            srl_canada_fact_pullToRefresh.isRefreshing = false
        }

    }

    private fun loadCanadaFactsList() {
        emptyView.invisible()
        mCanadaFactsViewModel.loadCanadaFacts()
    }

    private fun renderCanadaFactsList(canadaFactsView: CanadaFactsView?) {
        canadaFactsView?.title?.let { (activity as CanadaFactListActivity).setActionTitle(it) }
        canadaFactListAdapter.collection = canadaFactsView?.factRowEntity!!
        canadaFactList.visible()
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is ServerError -> renderFailure(R.string.failure_server_error)
            is CanadaFactsFailure.ListNotAvailable -> renderFailure(R.string.failure_canada_fact_list_unavailable)
            else -> renderFailure(R.string.failure_server_error)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        canadaFactList.visibility = View.GONE
        (activity as CanadaFactListActivity).setActionTitle("")
        emptyView.visible()
        hideProgress()
        notifyWithAction(message, ::loadCanadaFactsList)
    }
}
