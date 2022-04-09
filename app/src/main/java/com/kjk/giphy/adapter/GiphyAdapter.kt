package com.kjk.giphy.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kjk.giphy.databinding.ListItemGiphyBinding
import com.kjk.giphy.model.GiphyDataSender
import com.kjk.giphy.network.model.Data
import kotlin.math.log

class GiphyAdapter(
    private val dataSender: GiphyDataSender,
    private val callBackListener: OnCheckBoxClickListener
) : RecyclerView.Adapter<GiphyViewHolder>() {

    interface OnCheckBoxClickListener {
        fun getCheckedState(isChecked: Boolean, data: Data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyViewHolder {
        val binding = ListItemGiphyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GiphyViewHolder(dataSender, binding, callBackListener)
    }

    override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: ${dataSender.getGiphyDataList().size}")
        holder.bind(dataSender.getGiphyDataList()[position])
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount: ${dataSender.getGiphyDataList().size}")
        return dataSender.getGiphyDataList().size
    }

    companion object {
        private const val TAG = "GiphyAdapter"
    }

}