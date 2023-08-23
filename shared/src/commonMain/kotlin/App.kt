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
import kotlinx.serialization.json.Json
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.query
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.viewmodel.viewModel
import screens.CounterScreen
import screens.HelloComposable
import screens.HomeScreen
import screens.PokemonDetailScreen
import screens.PokemonListScreen

enum class Screens(val screenName: String, val route: String) {
    HOME("Home", "/home"),
    HELLO("Hello Page", "/hello"),
    COUNTER("Counter Page", "/counter"),
    POKE_NAMES("Pokemon Names", "/poke_names"),
    POKE_DETAILS("Pokemon Details", "/poke_details/{name}/{nationalDexNumber}")
}

@Composable
fun App() {
    val navigator = rememberNavigator()
    val isRootPage by navigator.canGoBack.collectAsState(initial = false)

    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json { coerceInputValues = true })
        }
    }

    val pokemonRepo = PokemonRepository(PokemonDataSource(httpClient))

    val counterViewModel = viewModel(modelClass = CounterViewModel::class) { CounterViewModel() }
    val helloViewModel = viewModel(modelClass = HelloViewModel::class) { HelloViewModel() }
    val pokemonListViewModel = viewModel(modelClass = PokemonListViewModel::class) { PokemonListViewModel(pokemonRepo) }
    val pokemonDetailViewModel = viewModel(modelClass = PokemonDetailViewModel::class) { PokemonDetailViewModel(pokemonRepo) }

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
                initialRoute = Screens.POKE_NAMES.route//Screens.HOME.route
            ) {
                scene(route = Screens.HOME.route) {
                    HomeScreen(navigator, Screens.entries, counterViewModel.counter.value)
                } // scene
                scene(route = Screens.HELLO.route) {
                    HelloComposable(helloViewModel)
                } // scene
                scene(route = Screens.COUNTER.route) {
                    CounterScreen(counterViewModel)
                } // scene
                scene(route = Screens.POKE_NAMES.route) {
                    PokemonListScreen(
                        pokemonListViewModel,
                        onDetailNavigate = { name, nationalDexNumber -> navigator.navigate("/" + Screens.POKE_DETAILS.route.split('/')[1] + "/$name/$nationalDexNumber") }
                    )
                } // scene
                scene(route = Screens.POKE_DETAILS.route) {
                    val name = it.path("name", "")!!
                    val nationalDexNumber = it.path("nationalDexNumber", 0)!!
                    PokemonDetailScreen(pokemonDetailViewModel, name, nationalDexNumber)
                } // scene
            } // NavHost
        } // Scaffold
    } // MaterialTheme
} // App

expect fun getPlatformName(): String