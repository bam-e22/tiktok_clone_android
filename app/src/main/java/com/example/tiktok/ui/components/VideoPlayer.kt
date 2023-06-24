@file:OptIn(ExperimentalAnimationApi::class)

package com.example.tiktok.ui.components

import android.annotation.SuppressLint
import android.net.Uri
import android.view.View
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.example.tiktok.R
import com.example.tiktok.model.PlayerState
import com.example.tiktok.ui.utils.Sizes

@SuppressLint("UnsafeOptInUsageError")
@Composable
fun VideoPlayer(
    modifier: Modifier = Modifier,
) {
    val videoPlayerState = rememberVideoPlayerState()
    var showPlayButton by remember {
        mutableStateOf(false)
    }
    val playerState = videoPlayerState.playerState.value
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                ) {
                    if (videoPlayerState.player.isPlaying) {
                        videoPlayerState.pause()
                        showPlayButton = true
                    } else {
                        videoPlayerState.play()
                        showPlayButton = false
                    }
                },
            factory = { context ->
                PlayerView(context).also {
                    it.useController = false
                    it.controllerAutoShow = false
                    it.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                    it.player = videoPlayerState.player.apply {
                        playWhenReady = true
                    }
                    videoPlayerState.prepare(Uri.parse("asset:///IMG_2507.MOV"))
                }
            },
            update = {
                it.visibility = if (playerState == PlayerState.READY) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }
        )
        AnimatedVisibility(
            visible = showPlayButton,
            enter = fadeIn(initialAlpha = 0.3f) + scaleIn(initialScale = 1.5f),
            exit = fadeOut(targetAlpha = 0.3f) + scaleOut(targetScale = 1.5f)
        ) {
            Icon(
                modifier = Modifier.size(Sizes.ExtraLargeIconSize),
                painter = painterResource(id = R.drawable.play_solid),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}
