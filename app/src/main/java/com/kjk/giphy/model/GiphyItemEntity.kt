package com.kjk.giphy.model

// 네트워크 통신 전, 임시 테스트 데이터
data class GiphyItemEntity(
    val title: String,
    val isFavorite: Boolean // 좋아요 선택 여부
)