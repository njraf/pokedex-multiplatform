package screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun CounterScreen() {
    var counter by remember { mutableStateOf(0) }
    Column {
        Text(text = counter.toString())
        Button(onClick = { counter += 1 }) { Text("Count") }
    }
}
