package com.kjk.giphy.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.giphy.sdk.ui.views.GiphyGridView
import com.kjk.giphy.adapter.GiphyAdapter
import com.kjk.giphy.databinding.FragmentTrendingBinding
import com.kjk.giphy.model.GiphyModel

class TrendingFragment : Fragment() {

    private var _binding: FragmentTrendingBinding? = null
    private val binding
        get() = _binding!!

    private val model = GiphyModel()

    private val giphyAdapter: GiphyAdapter by lazy {
        GiphyAdapter(model)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrendingBinding.inflate(inflater, container, false)
        initLayout()
        setData()
        //initListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initLayout() {
        Log.d(TAG, "initLayout: ")
        binding.trendingRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = giphyAdapter
        }
    }

    private fun setData() {
        model.setGiphyItemList()
    }

    private fun initListener() {

    }

    companion object {
        private const val TAG = "TrendingFragment"
        fun newInstance(): TrendingFragment {
            return TrendingFragment()
        }
    }
}