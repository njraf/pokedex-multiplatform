import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class PokemonListViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    private var _uiState = MutableStateFlow<PokemonUiState>(PokemonUiState.Loading)
    val uiState = _uiState.asStateFlow()

    val nationalDex = mutableListOf<PokemonEntry>()
    private val timeout = 10000L

    init {
        getNames(Regions.KANTO)
    }

    fun getNames(region: Regions) {
        viewModelScope.launch {
            _uiState.value = PokemonUiState.Loading
            try {
                val names = async {
                    withTimeout(timeout) {
                        pokemonRepository.getPokemon(region)
                    }
                }
                if (nationalDex.isEmpty()) {
                    val nationalDexNames = async {
                        withTimeout(timeout) {
                            pokemonRepository.getPokemon(Regions.NATIONAL)
                        }
                    }
                    nationalDex.addAll(nationalDexNames.await())
                }
                _uiState.value = PokemonUiState.Success(names.await())
            } catch (e: Exception) {
                _uiState.value = PokemonUiState.Error("ERROR: " + (e.message ?: "Unknown error"))
            }
        }
    }
}

sealed class PokemonUiState {
    data class Success(val pokemonNames: List<PokemonEntry>) : PokemonUiState()
    object Loading : PokemonUiState()
    data class Error(val message: String) : PokemonUiState()
}