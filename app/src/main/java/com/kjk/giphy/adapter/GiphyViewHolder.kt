package com.kjk.giphy.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kjk.giphy.databinding.ListItemGiphyBinding
import com.kjk.giphy.model.GiphyDataSender
import com.kjk.giphy.model.GiphyItemEntity
import com.kjk.giphy.network.model.Data

class GiphyViewHolder(
    private val dataSender: GiphyDataSender,
    private val binding: ListItemGiphyBinding,
    private val callBackListener: GiphyAdapter.OnCheckBoxClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Data) {
        Log.d(TAG, "bind: ${item.images.fixedWidth?.gifUrl}")
        binding.apply {
            Glide
                .with(giphyImageview.context)
                .load(item.images.fixedWidth?.gifUrl)
                .into(giphyImageview)

            giphyTextView.text = item.title
            giphyId.text = item.id
            favoriteCheckbox.setOnCheckedChangeListener { compoundButton, isChecked ->
                callBackListener.getCheckedState(isChecked, item)
            }
        }
    }
    companion object {
        private const val TAG = "GiphyViewHolder"
    }
}