package com.kjk.giphy.trending

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.kjk.giphy.R

import com.kjk.giphy.databinding.TrendingActivityBinding

class TrendingActivity : AppCompatActivity() {

    private lateinit var binding: TrendingActivityBinding

    private val viewModel: TrendingViewModel by viewModels {
        TrendingViewModelFactory(this.application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.trending_activity)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        observe()
    }

    /**
     * viewModel의 LiveData Observing
     */
    private fun observe() {
        viewModel.navigateToFavorite.observe(this, Observer { toMove ->
            if (toMove) {
                moveToFavoriteFragment()
                viewModel.onNavigateDone()
            }
        })
    }

    /**
     *  Favorite Button Click시
     *  Favorite Fragment로 이동한다.
     */
    private fun moveToFavoriteFragment() {
        this.findNavController(R.id.nav_host_fragment)
            .navigate(TrendingFragmentDirections.actionTrendingFragmentToFavoriteFragment())
    }
}