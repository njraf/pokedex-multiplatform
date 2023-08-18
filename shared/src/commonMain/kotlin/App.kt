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

enum class Screens(val route: String) {
    HOME("/home"),
    SCREEN2("/page2"),
    SCREEN3("/page3"),
    POKE_NAMES("/poke_names")
}

@Composable
fun App() {
    val navigator = rememberNavigator()
    val isRootPage by navigator.canGoBack.collectAsState(initial = false)

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
                initialRoute = Screens.HOME.route
            ) {
                scene(route = Screens.HOME.route) {
                    HomeScreen(navigator, Screens.entries.map { it.route }, counterViewModel.counter.value)
                } // scene
                scene(route = Screens.SCREEN2.route) {
                    HelloComposable(helloViewModel)
                } // scene
                scene(route = Screens.SCREEN3.route) {
                    CounterScreen(counterViewModel)
                } // scene
                scene(route = Screens.POKE_NAMES.route) {
                    PokemonListScreen(pokemonViewModel)
                } // scene
            } // NavHost
        } // Scaffold
    } // MaterialTheme
} // App

expect fun getPlatformName(): String