package com.mvvm_clean.about_canada.features.canada_facts.view.fragments

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvm_clean.about_canada.core.platform.BaseFragment
import com.mvvm_clean.about_canada.R
import com.mvvm_clean.about_canada.core.exception.Failure
import com.mvvm_clean.about_canada.core.exception.Failure.NetworkConnection
import com.mvvm_clean.about_canada.core.exception.Failure.ServerError
import com.mvvm_clean.about_canada.core.extension.failure
import com.mvvm_clean.about_canada.core.extension.invisible
import com.mvvm_clean.about_canada.core.extension.observe
import com.mvvm_clean.about_canada.core.extension.viewModel
import com.mvvm_clean.about_canada.core.extension.visible
import com.mvvm_clean.about_canada.core.navigation.Navigator
import com.mvvm_clean.about_canada.features.canada_facts.data.CanadaFactsFailure
import com.mvvm_clean.about_canada.features.canada_facts.view.CanadaFactsView
import com.mvvm_clean.about_canada.features.canada_facts.view.CanadaFactsViewModel
import com.mvvm_clean.about_canada.features.canada_facts.view.adapters.CanadaFactListAdapter
import kotlinx.android.synthetic.main.fragment_canada_facts.*
import javax.inject.Inject

class CanadaFactListFragment : BaseFragment() {

    @Inject lateinit var navigator: Navigator
    @Inject lateinit var canadaFactListAdapter: CanadaFactListAdapter

    private lateinit var mCanadaFactsViewModel : CanadaFactsViewModel

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
        loadCanadaFactsList()
    }


    private fun initializeView() {
        canadaFactList.layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.VERTICAL, false)

        canadaFactList.adapter = canadaFactListAdapter
    }

    private fun loadCanadaFactsList() {
        emptyView.invisible()
        canadaFactList.visible()
        showProgress()
        mCanadaFactsViewModel.loadCanadaFacts()
    }

    private fun renderCanadaFactsList(canadaFactsView: CanadaFactsView?) {
        canadaFactListAdapter.collection = canadaFactsView?.factRowEntity!!
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is ServerError -> renderFailure(R.string.failure_server_error)
            is CanadaFactsFailure.ListNotAvailable -> renderFailure(R.string.failure_canada_fact_list_unavailable)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        canadaFactList.invisible()
        emptyView.visible()
        hideProgress()
        notifyWithAction(message, R.string.action_refresh, ::loadCanadaFactsList)
    }
}
