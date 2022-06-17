package com.kjk.giphy.trending

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kjk.giphy.R
import com.kjk.giphy.data.domain.GiphyProperty
import com.kjk.giphy.databinding.ListItemGiphyBinding

class GiphyAdapter : RecyclerView.Adapter<GiphyAdapter.GiphyViewHolder>() {

    private val giphyList = arrayListOf<GiphyProperty>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyViewHolder {
        return GiphyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        holder.bind(giphyList[position])
    }

    override fun getItemCount(): Int {
        return giphyList.size
    }

    fun updateAll(updateList: List<GiphyProperty>) {
        giphyList.addAll(updateList)
        notifyDataSetChanged()
    }

    companion object {
        private const val TAG = "GiphyAdapter"
    }

    /**
     *  ViewHolder Class이다.
     */
    class GiphyViewHolder(
        private val binding: ListItemGiphyBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(giphy: GiphyProperty) {
            binding.giphy = giphy
            binding.executePendingBindings()
        }

        companion object {
            private const val TAG = "GiphyViewHolder"
            fun from(parent: ViewGroup): GiphyViewHolder  {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<ListItemGiphyBinding>(
                    layoutInflater,
                    R.layout.list_item_giphy,
                    parent,
                    false
                )
                return GiphyViewHolder(binding)
            }
        }
    }
}