package com.mezkall.speak

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


interface TextSpeaker {
    fun speak(text: String)
}