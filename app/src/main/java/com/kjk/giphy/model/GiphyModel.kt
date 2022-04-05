package com.kjk.giphy.model

class GiphyModel : GiphyDataSender {

    private var giphyItemList = arrayListOf<GiphyItemEntity>()

    override fun getGiphyItemList(): ArrayList<GiphyItemEntity> {
        return this.giphyItemList
    }

    fun setGiphyItemList() {
        repeat(MAX_ITEM_SIZE) {
            giphyItemList.add(GiphyItemEntity(it.toString(), false))
        }
    }

    companion object {
        private const val MAX_ITEM_SIZE = 100
    }
}