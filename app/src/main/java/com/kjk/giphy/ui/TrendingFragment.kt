package com.kjk.giphy.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kjk.giphy.adapter.GiphyAdapter
import com.kjk.giphy.databinding.FragmentTrendingBinding
import com.kjk.giphy.model.GiphyModel
import com.kjk.giphy.network.API_KEY_GIPHY
import com.kjk.giphy.network.BASE_URL_GIPHY
import com.kjk.giphy.network.GiphyService
import com.kjk.giphy.network.model.Data
import com.kjk.giphy.network.model.ResponseTrendingGifs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TrendingFragment :
    Fragment(),
    GiphyAdapter.OnCheckBoxClickListener {

    private var _binding: FragmentTrendingBinding? = null
    private val binding
        get() = _binding!!

    private val model = GiphyModel()

    private val giphyAdapter: GiphyAdapter by lazy {
        GiphyAdapter(model, this@TrendingFragment)
    }

    private lateinit var retrofit: Retrofit
    private lateinit var giphyService: GiphyService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        //loadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrendingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRetrofit()
        loadInitialData()
    }

    private fun initLayout() {
        Log.d(TAG, "initLayout: ")
        binding.trendingRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = giphyAdapter
        }
    }

    private fun initRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_GIPHY)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun loadInitialData() {
        giphyService = retrofit.create(GiphyService::class.java)
        giphyService.getTrendingList(API_KEY_GIPHY).enqueue(
            object : Callback<ResponseTrendingGifs> {
                override fun onResponse(
                    call: Call<ResponseTrendingGifs>,
                    response: Response<ResponseTrendingGifs>
                ) {
                    if (response.isSuccessful.not()) {
                        return
                    }

                    response.body()?.let {
                        model.setGiphyDataList(it.data)
                        initLayout()
                    }
                }

                override fun onFailure(call: Call<ResponseTrendingGifs>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }
            }
        )
    }

    override fun getCheckedState(isChecked: Boolean, data: Data) {
        Log.d(TAG, "getCheckedState: ${isChecked}, ${data.id}")
        if (isChecked) {
            // Favorite DataBase에 삽입

        } else {
            // DataBase에서 제거
        }
    }

    companion object {
        private const val TAG = "TrendingFragment"
        fun newInstance(): TrendingFragment {
            return TrendingFragment()
        }
    }
}