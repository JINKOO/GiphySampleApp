package com.kjk.giphy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.kjk.giphy.databinding.ActivityMainBinding
import com.kjk.giphy.favorite.FavoriteFragment
import com.kjk.giphy.trending.TrendingFragment

class MainActivity :
    AppCompatActivity(),
    View.OnClickListener {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initListener()
        initLayout()
    }

    private fun initLayout() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            val fragment = TrendingFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    private fun initListener() {
        binding.apply {
            toMainListButton.setOnClickListener(this@MainActivity)
            toFavoriteListButton.setOnClickListener(this@MainActivity)
        }
    }

    private fun moveToTrendingFragment() {
        Log.d(TAG, "moveToTrendingFragment: ")
        val fragment = TrendingFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun moveToFavoriteFragment() {
        Log.d(TAG, "moveToFavoriteFragment: ")
        val fragment = FavoriteFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onClick(v: View?) {
        binding.apply {
            when(v) {
                toMainListButton -> {
                    moveToTrendingFragment()
                }
                toFavoriteListButton -> {
                    moveToFavoriteFragment()
                }
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}