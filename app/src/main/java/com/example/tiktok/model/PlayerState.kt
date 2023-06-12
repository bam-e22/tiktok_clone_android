package com.example.tiktok.model

sealed interface PlayerState {
    object IDLE: PlayerState
    object BUFFERING: PlayerState
    object READY: PlayerState
    object ERROR: PlayerState
}
