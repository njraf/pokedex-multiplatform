import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.viewmodel.viewModel
import screens.CounterScreen
import screens.HelloComposable
import screens.HomeScreen
import screens.PokemonListScreen

@Composable
fun App() {
    val navigator = rememberNavigator()
    val isRootPage by navigator.canGoBack.collectAsState(initial = false)
    val routes = listOf("/page1", "/page2", "/page3", "/page4")

    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    val counterViewModel = viewModel(modelClass = CounterViewModel::class) { CounterViewModel() }
    val helloViewModel = viewModel(modelClass = HelloViewModel::class) { HelloViewModel() }
    val pokemonViewModel = viewModel(modelClass = PokemonListViewModel::class) { PokemonListViewModel(PokemonRepository(PokemonDataSource(httpClient))) }

    MaterialTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
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
                    HomeScreen(navigator, routes, counterViewModel.counter.value)
                } // scene
                scene(route = routes[1]) {
                    HelloComposable(helloViewModel)
                } // scene
                scene(route = routes[2]) {
                    CounterScreen(counterViewModel)
                } // scene
                scene(route = routes[3]) {
                    PokemonListScreen(pokemonViewModel)
                } // scene
            } // NavHost
        } // Scaffold
    } // MaterialTheme
} // App

expect fun getPlatformName(): String