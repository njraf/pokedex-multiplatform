import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
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
            try {
                names = pokemonRepository.getPokemon(region)
                _uiState.update {
                    it.copy(pokemonNames = names)
                }
            } catch (e: Exception) {
                println("Error: " + e.message)
            }
        }
        return names
    }
}

data class PokemonUiState(val pokemonNames: List<PokemonEntry> = emptyList())