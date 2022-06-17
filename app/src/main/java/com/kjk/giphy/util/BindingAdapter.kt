package com.kjk.giphy.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kjk.giphy.R
import com.kjk.giphy.data.domain.GiphyProperty
import com.kjk.giphy.trending.GiphyAdapter
import com.kjk.giphy.trending.GiphyApiStatus
import timber.log.Timber

@BindingAdapter("progressbar")
fun hideProgressBar(progressBar: ProgressBar, apiStatus: GiphyApiStatus?) {
    apiStatus?.let {
        progressBar.run {
            visibility = when (apiStatus) {
                GiphyApiStatus.LOADING -> {
                    View.VISIBLE
                }
                GiphyApiStatus.ERROR,
                GiphyApiStatus.DONE -> {
                    View.GONE
                }
            }
        }
    }
}

@BindingAdapter("giphyPropertyList")
fun setGiphyList(recyclerView: RecyclerView, giphyProperties: List<GiphyProperty>?) {
    val adapter = recyclerView.adapter as GiphyAdapter
    giphyProperties?.let {
        adapter.updateAll(giphyProperties)
    }
}

@BindingAdapter("giphyThumbnail")
fun setThumbnailImage(imageView: ImageView, thumbNailUrl: String?) {
    Timber.d("${thumbNailUrl}")
    thumbNailUrl?.let {
        val imgSrcUrl = thumbNailUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imgSrcUrl)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_img)
                .error(R.drawable.ic_broken_image))
            .into(imageView)
    }
}

@BindingAdapter("errorImage")
fun setErrorImage(imageView: ImageView, apiStatus: GiphyApiStatus?) {
    apiStatus?.let {
        imageView.run {
            visibility = when (apiStatus) {
                GiphyApiStatus.ERROR -> {
                   View.VISIBLE
                }
                GiphyApiStatus.DONE,
                GiphyApiStatus.LOADING -> {
                    View.GONE
                }
            }
        }
    }
}