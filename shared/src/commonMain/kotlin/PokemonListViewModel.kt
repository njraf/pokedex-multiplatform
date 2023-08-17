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

class PokemonListViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    private var _uiState = MutableStateFlow<PokemonUiState>(PokemonUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getNames(Regions.KANTO)
    }

    fun getNames(region: Regions): List<PokemonEntry> {
        var names: List<PokemonEntry> = emptyList()
        viewModelScope.launch {
            names = pokemonRepository.getPokemon(region)
            _uiState.update {
                it.copy(pokemonNames = names)
            }
        }
        return names
    }
}

data class PokemonUiState(val pokemonNames: List<PokemonEntry> = emptyList())