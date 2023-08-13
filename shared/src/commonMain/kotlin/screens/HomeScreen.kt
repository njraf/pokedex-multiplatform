package screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun HomeScreen(navigator: Navigator, routes: List<String>, count: Int) {
    Column {
        Button(onClick = { navigator.navigate(routes[1]) }) { Text("To Page1") }
        Button(onClick = { navigator.navigate(routes[2]) }) { Text("To Page2") }
        Text(count.toString())
    }
}