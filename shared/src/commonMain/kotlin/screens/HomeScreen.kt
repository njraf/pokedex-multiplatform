package screens

import Screens
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.Navigator
import kotlin.enums.EnumEntries

@Composable
fun HomeScreen(navigator: Navigator, routes: EnumEntries<Screens>, count: Int) {
    Column {
        Button(onClick = { navigator.navigate(routes[1].route) }) { Text("To ${routes[1].screenName}") }
        Button(onClick = { navigator.navigate(routes[2].route) }) { Text("To ${routes[2].screenName}") }
        Button(onClick = { navigator.navigate(routes[3].route) }) { Text("To ${routes[3].screenName}") }
        Text(count.toString())
    }
}