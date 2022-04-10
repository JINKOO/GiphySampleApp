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
import com.kjk.giphy.MainViewModel
import com.kjk.giphy.GiphyAdapter
import com.kjk.giphy.data.database.Giphy
import com.kjk.giphy.databinding.FragmentFavoriteBinding
import com.kjk.giphy.data.network.model.Data

class FavoriteFragment :
    Fragment(),
    GiphyAdapter.OnCheckBoxClickListener {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private val favoriteAdapter by lazy {
        GiphyAdapter(this@FavoriteFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()

        viewModel.giphyFavoriteLiveData.observe(
            viewLifecycleOwner
        ) {
            Log.d(TAG, "onViewCreated: ${it.size}")
            if (it.isEmpty()) {
                binding.emptyFavoriteTextview.visibility = View.VISIBLE
            } else {
                binding.emptyFavoriteTextview.visibility = View.GONE
                favoriteAdapter.updateAll(it)
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        _binding = null
    }


    private fun initLayout() {
        binding.favoriteRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = favoriteAdapter
        }
    }

    override fun getCheckedState(isChecked: Boolean, giphy: Giphy) {
        Log.d(TAG, "getCheckedState: ${isChecked}, ${giphy.id}")
    }

    companion object {
        private const val TAG = "FavoriteFragment"
        fun newInstance(): FavoriteFragment {
            return FavoriteFragment()
        }
    }
}