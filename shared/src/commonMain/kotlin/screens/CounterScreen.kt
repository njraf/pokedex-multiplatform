package screens

import CounterViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import moe.tlaster.precompose.viewmodel.viewModel

@Composable
fun CounterScreen(viewModel: CounterViewModel) {
    var counter by remember { viewModel.counter }
    Column {
        Text(text = counter.toString())
        Button(onClick = { counter += 1 }) { Text("Count") }
    }
}
