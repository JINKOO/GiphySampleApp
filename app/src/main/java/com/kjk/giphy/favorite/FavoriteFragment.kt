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
 *  아직 미완성 class
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