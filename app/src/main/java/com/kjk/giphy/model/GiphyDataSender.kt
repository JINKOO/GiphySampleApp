package com.kjk.giphy.model

interface GiphyDataSender {
    fun getGiphyItemList(): ArrayList<GiphyItemEntity>
}