package com.kjk.giphy.trending

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kjk.giphy.R
import com.kjk.giphy.databinding.FragmentTrendingBinding
import timber.log.Timber

class TrendingFragment : Fragment() {

    private lateinit var binding: FragmentTrendingBinding

    /**
     * viewModel 정의
     */
    private val viewModel: TrendingViewModel by activityViewModels()

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

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setHasOptionsMenu(true)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.item_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     *  Navigation Bar menu item을 클릭한 경우
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.show_all -> {
                // showAll
                Timber.d("Show All")
            }
            R.id.favorite-> {
                // favorite
                Timber.d("favorite")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initLayout() {
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = giphyAdapter
        }
    }


    private fun observe() {
        // LiveData Observing 추가.
        viewModel.navigateToFavorite.observe(viewLifecycleOwner, Observer { toMove ->
            if (toMove) {
                // Favorite Fragment로 이동
                moveToFavoriteFragment()
            }
        })
    }

    private fun moveToFavoriteFragment() {
        this.findNavController()
            .navigate(TrendingFragmentDirections.actionTrendingFragmentToFavoriteFragment())
    }

    companion object {
        private const val TAG = "TrendingFragment"
    }
}