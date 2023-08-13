import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    val navigator = rememberNavigator()
    val isRootPage by navigator.canGoBack.collectAsState(initial = false)
    val routes = listOf("/page1", "/page2", "/page3")

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(navigationIcon = {
                    if (isRootPage) {
                        IconButton(onClick = { navigator.goBack() }) {
                            Icon(Icons.Filled.ArrowBack, "")
                        }
                    }
                }, title = { Text("Pokedex") })
            }
        ) {
            NavHost(
                navigator = navigator,
                initialRoute = routes[0]
            ) {
                scene(route = routes[0]) {
                    Column {
                        Button(onClick = { navigator.navigate(routes[1]) }) { Text("To Page1") }
                        Button(onClick = { navigator.navigate(routes[2]) }) { Text("To Page2") }
                    }
                } // scene
                scene(route = routes[1]) {
                    var greetingText by remember { mutableStateOf("Hello, World!") }
                    var showImage by remember { mutableStateOf(false) }
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = {
                            greetingText = "Hello, ${getPlatformName()}"
                            showImage = !showImage
                        }) {
                            Text(greetingText)
                        }
                        AnimatedVisibility(showImage) {
                            Image(
                                painterResource("compose-multiplatform.xml"),
                                null
                            )
                        }
                    }
                } // scene
                scene(route = routes[2]) {
                    var counter by remember { mutableStateOf(0) }
                    Column {
                        Text(text = counter.toString())
                        Button(onClick = { counter += 1 }) { Text("Count") }
                    }
                } // scene
            } // NavHost
        } // Scaffold
    } // MaterialTheme
} // App

expect fun getPlatformName(): String