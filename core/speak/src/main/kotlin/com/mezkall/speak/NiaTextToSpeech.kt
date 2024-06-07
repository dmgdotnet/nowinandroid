package com.mezkall.speak

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

internal class NiaTextToSpeech @Inject constructor(
    @ApplicationContext private val context: Context,
) : TextSpeaker {
    private lateinit var tts: TextToSpeech
    private lateinit var initStamp: String
    init{
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val date = Date()
        initStamp = dateFormat.format(date)

        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts.setLanguage(Locale.US)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(context, "Language not supported", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "TextToSpeech initialized", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "TextToSpeech initialization failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun speak(text: String) {
        Log.d("TextToSpeech", "$initStamp:: Speaker started")
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
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