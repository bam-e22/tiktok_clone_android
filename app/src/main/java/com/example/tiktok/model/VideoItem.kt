package com.example.tiktok.model

data class VideoItem(
    val creator: String,
    val title: String,
    val description: String,
    val tags: List<String>
)
