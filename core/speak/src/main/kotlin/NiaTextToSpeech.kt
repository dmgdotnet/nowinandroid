import android.content.Context
import android.widget.Toast
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class NiaTextToSpeech @Inject constructor(
    @ApplicationContext private val context: Context,
) : TextSpeaker {
    override fun speak(text: String) {
        Toast.makeText(context, "This is a Toast num 3", Toast.LENGTH_SHORT).show()
    }
}
