package com.mezkall.speak

import android.content.Context
import android.widget.Toast
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


internal class NiaTextToSpeech @Inject constructor(
    @ApplicationContext private val context: Context,
) : TextSpeaker {
    override fun speak(text: String) {
        Toast.makeText(context, "This is a Toast num 3", Toast.LENGTH_SHORT).show()
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SpeakerModule {
    @Binds
    abstract fun bindTextSpeaker(
        niaTextToSpeech: NiaTextToSpeech
    ): TextSpeaker
}