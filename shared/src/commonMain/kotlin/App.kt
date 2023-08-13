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
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.viewmodel.viewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import screens.CounterScreen
import screens.HelloComposable
import screens.HomeScreen

@Composable
fun App() {
    val navigator = rememberNavigator()
    val isRootPage by navigator.canGoBack.collectAsState(initial = false)
    val routes = listOf("/page1", "/page2", "/page3")

    val counterViewModel = viewModel(modelClass = CounterViewModel::class) { CounterViewModel() }

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
                    HomeScreen(navigator, routes)
                } // scene
                scene(route = routes[1]) {
                    HelloComposable()
                } // scene
                scene(route = routes[2]) {
                    CounterScreen(counterViewModel)
                } // scene
            } // NavHost
        } // Scaffold
    } // MaterialTheme
} // App

expect fun getPlatformName(): String