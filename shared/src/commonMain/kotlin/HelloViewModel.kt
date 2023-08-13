import androidx.compose.runtime.mutableStateOf
import moe.tlaster.precompose.viewmodel.ViewModel

class HelloViewModel() : ViewModel() {
    val greetingText = mutableStateOf("Hello, World!")
    val showImage = mutableStateOf(false)
}