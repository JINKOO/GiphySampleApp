package com.kjk.giphy.favorite

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kjk.giphy.databinding.FragmentFavoriteBinding
import com.kjk.giphy.trending.TrendingViewModel
import com.kjk.giphy.trending.GiphyAdapter

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding

    private val viewModel by lazy {
        ViewModelProvider(this).get(TrendingViewModel::class.java)
    }

    private val favoriteAdapter by lazy {
        GiphyAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        initLayout()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initLayout() {
        binding.favoriteRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = favoriteAdapter
        }
    }

    companion object {
        private const val TAG = "FavoriteFragment"
    }
}