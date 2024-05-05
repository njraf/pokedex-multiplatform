import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

const val TIMEOUT = 10000L

class PokemonDetailViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    private var _uiState = MutableStateFlow<PokemonDetailsUiState>(PokemonDetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val jobs = mutableListOf<Job>()

    override fun onCleared() {
        super.onCleared()
        for (job in jobs) {
            job.cancel()
        }
        jobs.clear()
    }

    fun getPokemonDetails(name: String, nationalDexNumber: Int) {
        viewModelScope.launch {
            _uiState.value = PokemonDetailsUiState.Loading
            try {
                //val pokemonDetails = pokemonRepository.getPokemonDetails(name)
                val pokemonDetails = withTimeout(TIMEOUT) { async { pokemonRepository.getPokemonDetails(nationalDexNumber) } }
                val pokemonSpeciesModel = withTimeout(TIMEOUT) { async { pokemonRepository.getPokemonSpecies(name) } }
                jobs.add(pokemonDetails)
                jobs.add(pokemonSpeciesModel)
                _uiState.value = PokemonDetailsUiState.Success(
                    pokemonDetails = pokemonDetails.await(),
                    pokemonSpeciesModel = pokemonSpeciesModel.await()
                )
            } catch (e: Exception) {
                println("Error: " + e.message)
                _uiState.value = PokemonDetailsUiState.Error("ERROR: " + (e.message ?: "Unknown error"))
            } finally {
                jobs.clear()
            }
        }
    }
}

sealed class PokemonDetailsUiState {
    data class Success(val pokemonDetails: PokemonDetails = PokemonDetails(),
                       val pokemonSpeciesModel: PokemonSpeciesModel = PokemonSpeciesModel()
    ) : PokemonDetailsUiState()
    object Loading : PokemonDetailsUiState()
    data class Error(val message: String) : PokemonDetailsUiState()
}