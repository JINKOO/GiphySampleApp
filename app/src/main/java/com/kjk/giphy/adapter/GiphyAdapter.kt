package com.kjk.giphy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kjk.giphy.databinding.ListItemGiphyBinding
import com.kjk.giphy.model.GiphyDataSender

class GiphyAdapter(
    val dataSender: GiphyDataSender
) : RecyclerView.Adapter<GiphyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyViewHolder {
        val binding = ListItemGiphyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GiphyViewHolder(dataSender, binding)
    }

    override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        holder.bind(dataSender.getGiphyItemList()[position])
    }

    override fun getItemCount(): Int {
        return dataSender.getGiphyItemList().size
    }


}