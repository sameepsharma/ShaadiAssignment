package com.example.shaadiassignment.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shaadiassignment.R
import com.example.shaadiassignment.db.MatchesDB
import com.example.shaadiassignment.db.dao.MatchesDao
import com.example.shaadiassignment.db.entities.MatchesEntity
import com.example.shaadiassignment.repository.MatchesRepo
import com.example.shaadiassignment.repository.ResultCallback
import com.example.shaadiassignment.rest.FetchService
import com.example.shaadiassignment.rest.RetrofitProvider
import com.example.shaadiassignment.ui.MatchesAdapter

class MainFragment : Fragment(), MatchesAdapter.MatchesClickListener, ResultCallback {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: MatchesAdapter

    private lateinit var matchesRepo: MatchesRepo

    private lateinit var matchesDao: MatchesDao

    private lateinit var rvMatches : RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        matchesDao = MatchesDB.getDatabase(requireContext().applicationContext).matchesDao()
        matchesRepo = MatchesRepo(
            RetrofitProvider.retrofit!!.create(FetchService::class.java),
            matchesDao,
            requireContext().applicationContext,
            this
        )
        viewModel = ViewModelProvider(viewModelStore, MainViewModelFactory(matchesRepo)).get(MainViewModel::class.java)

        rvMatches = requireView().findViewById(R.id.rv_matches)
        rvMatches.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

        observeData()

    }

    private fun observeData() {

        viewModel.isLoading.observe(viewLifecycleOwner,
            {
                //TODO : Show/Hide Loader
            })

        viewModel.matchesLiveData.observe(viewLifecycleOwner,
            { matches ->

                Log.e("MatchSizeObs : ", "${matches.size} <<<")
                adapter = MatchesAdapter(this, matches.toMutableList())
                rvMatches.adapter = adapter

            })

    }

    override fun onAcceptClick(entity: MatchesEntity) {

        Toast.makeText(requireContext(), "Accepted !!", Toast.LENGTH_LONG).show()
        viewModel.isAccepeted(entity)

    }

    override fun onDeclineClick() {
        Toast.makeText(requireContext(), "Declined !!", Toast.LENGTH_LONG).show()
    }

    override fun onReceivedMatches(list: List<MatchesEntity>) {
        rvMatches.adapter = MatchesAdapter(this, list.toMutableList())
    }

    override fun onError(msg: String) {
        Toast.makeText(requireContext(), "Couldn't fetch Matches. Please try again later.",Toast.LENGTH_LONG).show()
    }

}