package com.kjk.giphy.trending

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kjk.giphy.GiphyAdapter
import com.kjk.giphy.MainViewModel
import com.kjk.giphy.data.database.Giphy
import com.kjk.giphy.databinding.FragmentTrendingBinding
import com.kjk.giphy.data.network.API_KEY_GIPHY
import com.kjk.giphy.data.network.BASE_URL_GIPHY
import com.kjk.giphy.data.network.GiphyService
import com.kjk.giphy.data.network.model.Data
import com.kjk.giphy.data.network.model.ResponseTrendingGifs
import com.kjk.giphy.model.GiphyModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TrendingFragment :
    Fragment(),
    GiphyAdapter.OnCheckBoxClickListener {

    private var _binding: FragmentTrendingBinding? = null
    private val binding
        get() = _binding!!


    private val mainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private val giphyAdapter: GiphyAdapter by lazy {
        GiphyAdapter(this@TrendingFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrendingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
        loadInitialData()

        mainViewModel.giphyLiveData.observe(
            viewLifecycleOwner
        ) {
            if (it.isEmpty()) {
                
            } else {
                Log.d(TAG, "onViewCreated: ${it.size}")
                giphyAdapter.updateAll(it)
            }
        }
    }

    private fun initLayout() {
        Log.d(TAG, "initLayout: ")
        binding.trendingRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = giphyAdapter
        }
    }


    private fun loadInitialData() {
        Log.d(TAG, "loadInitialData: ")
        mainViewModel.getInitialGiphyList()
    }

    override fun getCheckedState(isChecked: Boolean, giphy: Giphy) {
        Log.d(TAG, "getCheckedState: ${isChecked}, ${giphy}")
        if (isChecked) {
            mainViewModel.updateGiphy(giphy)
        } else {

        }
    }

    companion object {
        private const val TAG = "TrendingFragment"
        fun newInstance(): TrendingFragment {
            return TrendingFragment()
        }
    }
}