package com.example.tiktok.ui.components

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import com.example.tiktok.delegate.VideoPlayerDelegate
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@UnstableApi
@Stable
class VideoPlayerState @Inject constructor(
    private val delegate: VideoPlayerDelegate,
) {
    val playerState @Composable get() = delegate.playerState.collectAsStateWithLifecycle()
    val player: Player get() = delegate.exoPlayer
    fun prepare(uri: Uri) = delegate.prepare(uri)
    fun play() = delegate.play()
    fun pause() = delegate.pause()
    fun release() = delegate.release()
}

@EntryPoint
@InstallIn(SingletonComponent::class)
private interface VideoPlayerStateInjector {
    fun createState(): VideoPlayerState
}

@UnstableApi
@Composable
fun rememberVideoPlayerState(): VideoPlayerState {
    val context = LocalContext.current
    val videoPlayerState = remember {
        val injector = EntryPointAccessors
            .fromApplication<VideoPlayerStateInjector>(context)
        injector.createState()
    }
    DisposableEffect(Unit) {
        onDispose {
            videoPlayerState.release()
        }
    }
    return videoPlayerState
}
