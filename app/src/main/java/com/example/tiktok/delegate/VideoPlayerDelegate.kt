package com.example.tiktok.delegate

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.tiktok.domain.di.IoDispatcher
import com.example.tiktok.model.PlayerState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class VideoPlayerDelegate @Inject constructor(
    @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : CoroutineScope {
    override val coroutineContext: CoroutineContext = ioDispatcher + SupervisorJob()

    val exoPlayer: ExoPlayer = ExoPlayer.Builder(context).build()

    private val _playerState = MutableStateFlow<PlayerState>(PlayerState.IDLE)
    val playerState = _playerState.asStateFlow()

    private val listener = object : Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            launch {
                when (playbackState) {
                    Player.STATE_IDLE -> _playerState.emit(PlayerState.IDLE)
                    Player.STATE_BUFFERING -> _playerState.emit(PlayerState.BUFFERING)
                    else -> _playerState.emit(PlayerState.READY)
                }
            }
        }
    }

    fun prepare(uri: Uri) {
        val mediaItem = MediaItem.fromUri(uri)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.addListener(listener)
        exoPlayer.repeatMode = Player.REPEAT_MODE_ALL
        exoPlayer.prepare()
    }

    fun play() {
        if (!exoPlayer.isPlaying) {
            exoPlayer.play()
        }
    }

    fun pause() {
        if (exoPlayer.isPlaying) {
            exoPlayer.pause()
        }
    }

    fun release() {
        exoPlayer.removeListener(listener)
        exoPlayer.release()
    }
}
