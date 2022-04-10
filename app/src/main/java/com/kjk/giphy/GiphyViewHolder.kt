package com.kjk.giphy

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kjk.giphy.data.database.Giphy
import com.kjk.giphy.databinding.ListItemGiphyBinding
import com.kjk.giphy.model.GiphyDataSender
import com.kjk.giphy.data.network.model.Data

class GiphyViewHolder(
    private val binding: ListItemGiphyBinding,
    private val callBackListener: GiphyAdapter.OnCheckBoxClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(giphy: Giphy) {
        binding.apply {
            Glide
                .with(giphyImageview.context)
                .load(giphy.thumbnailUrl)
                .into(giphyImageview)

            favoriteCheckbox.isChecked = giphy.isFavorite
            favoriteCheckbox.setOnCheckedChangeListener { compoundButton, isChecked ->
                callBackListener.getCheckedState(isChecked, giphy)
            }
            giphyTextView.text = giphy.title
        }
    }
    companion object {
        private const val TAG = "GiphyViewHolder"
    }
}