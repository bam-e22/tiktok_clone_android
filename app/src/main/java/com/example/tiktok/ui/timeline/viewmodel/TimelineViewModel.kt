package com.example.tiktok.ui.timeline.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiktok.model.VideoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimelineViewModel @Inject constructor(
) : ViewModel() {
    private val _videoItems = MutableStateFlow<List<VideoItem>>(emptyList())
    val videoItems: StateFlow<List<VideoItem>> = _videoItems.asStateFlow()

    init {
        fetchVideoItems()
    }

    private fun fetchVideoItems() {
        val dummy = listOf(
            VideoItem(
                creator = "voluptaria",
                title = "orci",
                description = "habitant",
                tags = listOf("abc", "123")
            ),
            VideoItem(
                creator = "maecenas",
                title = "ferri",
                description = "ceteros",
                tags = listOf()
            )
        )
        viewModelScope.launch {
            _videoItems.emit(dummy)
        }
    }
}
