package com.kjk.giphy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kjk.giphy.data.database.Giphy
import com.kjk.giphy.databinding.ListItemGiphyBinding
import com.kjk.giphy.model.GiphyDataSender
import com.kjk.giphy.data.network.model.Data

class GiphyAdapter(
    private val callBackListener: OnCheckBoxClickListener
) : RecyclerView.Adapter<GiphyViewHolder>() {

    interface OnCheckBoxClickListener {
        fun getCheckedState(isChecked: Boolean, giphy: Giphy)
    }

    private val giphyList = arrayListOf<Giphy>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyViewHolder {
        val binding = ListItemGiphyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GiphyViewHolder(binding, callBackListener)
    }

    override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        holder.bind(giphyList[position])
    }

    override fun getItemCount(): Int {
        return giphyList.size
    }

    fun updateAll(updateList: List<Giphy>) {
        giphyList.addAll(updateList)
        notifyDataSetChanged()
    }

    companion object {
        private const val TAG = "GiphyAdapter"
    }

}