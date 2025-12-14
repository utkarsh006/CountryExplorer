package com.example.myapplication.presentation.intent

sealed class MainIntent {
    object GetNews : MainIntent()
    object Refresh : MainIntent()
}