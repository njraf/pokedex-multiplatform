import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class PokemonListViewModel : ViewModel() {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    override fun onCleared() {
        super.onCleared()
        httpClient.close()
    }

    private var _uiState = MutableStateFlow<PokemonUiState>(PokemonUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getNames()
    }

    private fun getNames(): List<PokemonEntry> {
        var names: List<PokemonEntry> = emptyList()
        viewModelScope.launch {
            val pokedex = httpClient.get("https://pokeapi.co/api/v2/pokedex/2/").body<Pokedex>()
            names = pokedex.pokemon_entries
            _uiState.update {
                it.copy(pokemonNames = names)
            }
        }
        return names
    }
}

data class PokemonUiState(val pokemonNames: List<PokemonEntry> = emptyList())