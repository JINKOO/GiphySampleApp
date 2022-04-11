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

        mainViewModel.giphyLiveData.observe(
            viewLifecycleOwner
        ) {
            if (it.isEmpty()) {
                Log.d(TAG, "onViewCreated: ${it.size}")
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

    override fun getCheckedState(isChecked: Boolean, giphy: Giphy) {
        Log.d(TAG, "getCheckedState: ${isChecked}, ${giphy.id}")
        mainViewModel.updateGiphy(giphy)
    }

    companion object {
        private const val TAG = "TrendingFragment"
        fun newInstance(): TrendingFragment {
            return TrendingFragment()
        }
    }
}