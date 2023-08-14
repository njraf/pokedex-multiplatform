import androidx.compose.runtime.mutableStateOf
import moe.tlaster.precompose.viewmodel.ViewModel

class CounterViewModel : ViewModel() {
    val counter = mutableStateOf(0)
}
