package com.kjk.giphy.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kjk.giphy.R
import com.kjk.giphy.databinding.FragmentFavoriteBinding
import com.kjk.giphy.trending.TrendingViewModel
import com.kjk.giphy.trending.GiphyAdapter

/**
 *  Trending Fragment에서 사용자가 item을 찜했을 경우,
 *  찜한 아이템만, 보여주는 Fragment
 *  2022-06-24현재 아직 구현 중.
 */
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding

    private val viewModel: TrendingViewModel by activityViewModels()

    private val favoriteAdapter by lazy {
        GiphyAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_favorite,
            container,
            false
        )

        initLayout()

        return binding.root
    }


    private fun initLayout() {
        binding.favoriteRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(MAX_SPAN_COUNT, LinearLayoutManager.VERTICAL)
            adapter = favoriteAdapter
        }
    }

    companion object {
        private const val TAG = "FavoriteFragment"
        private const val MAX_SPAN_COUNT = 2
    }
}