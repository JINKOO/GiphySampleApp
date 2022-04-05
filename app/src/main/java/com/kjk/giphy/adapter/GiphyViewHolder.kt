package com.kjk.giphy.adapter

import androidx.recyclerview.widget.RecyclerView
import com.kjk.giphy.databinding.ListItemGiphyBinding
import com.kjk.giphy.model.GiphyDataSender
import com.kjk.giphy.model.GiphyItemEntity

class GiphyViewHolder(
    private val dataSender: GiphyDataSender,
    private val binding: ListItemGiphyBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: GiphyItemEntity) {
        binding.apply {
            giphyTextView.text = item.title
        }
    }
}