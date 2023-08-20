import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class PokemonDetailViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    private var _uiState = MutableStateFlow<PokemonDetailsUiState>(PokemonDetailsUiState())
    val uiState = _uiState.asStateFlow()

    fun getPokemonDetails(name: String) {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(pokemonDetails = pokemonRepository.getPokemonDetails(name))
                }
            } catch (e: Exception) {
                println("Error: " + e.message)
            }
        }
    }
}

data class PokemonDetailsUiState(val pokemonDetails: PokemonDetails? = null)