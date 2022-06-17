package com.kjk.giphy.trending

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kjk.giphy.R
import com.kjk.giphy.data.database.GiphyDatabaseEntity
import com.kjk.giphy.databinding.FragmentTrendingBinding

class TrendingFragment : Fragment() {

    private lateinit var binding: FragmentTrendingBinding

    /**
     * viewModel 정의
     */
    private val viewModel by lazy {
        val activity = requireNotNull(activity).application
        ViewModelProvider(this, TrendingViewModelFactory(activity))
            .get(TrendingViewModel::class.java)
    }

    /**
     *  recyclerview에 사용할
     *  adapter 정의
     */
    private val giphyAdapter: GiphyAdapter by lazy {
        GiphyAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_trending,
            container,
            false
        )

        initLayout()

        binding.apply {
            viewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
    }

    private fun initLayout() {
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = giphyAdapter
        }
    }

    /**
     * viewModel의 LiveData Observing
     */
    private fun observe() {
        // LiveData Observing 추가.
    }

    companion object {
        private const val TAG = "TrendingFragment"
    }
}