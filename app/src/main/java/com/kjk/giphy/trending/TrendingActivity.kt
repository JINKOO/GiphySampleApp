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

    /**
     *  viewModel선언
     *  이 viewModel을 fragment에서도 사용할 수 있도록 한다.
     */
    private val viewModel: TrendingViewModel by viewModels {
        TrendingViewModelFactory(this.application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.trending_activity)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

    }
}