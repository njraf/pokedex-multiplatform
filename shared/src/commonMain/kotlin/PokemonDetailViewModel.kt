import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class PokemonDetailViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    private var _uiState = MutableStateFlow(PokemonDetailsUiState())
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
            try {
                //val pokemonDetails = pokemonRepository.getPokemonDetails(name)
                val pokemonDetails = async { pokemonRepository.getPokemonDetails(nationalDexNumber) }
                val pokemonSpeciesModel = async { pokemonRepository.getPokemonSpecies(name) }
                jobs.add(pokemonDetails)
                jobs.add(pokemonSpeciesModel)
                _uiState.update {
                    it.copy(
                        pokemonDetails = pokemonDetails.await(),
                        pokemonSpeciesModel = pokemonSpeciesModel.await()
                    )
                }
            } catch (e: Exception) {
                println("Error: " + e.message)
            } finally {
                jobs.clear()
            }
        }
    }
}

data class PokemonDetailsUiState(
    val pokemonDetails: PokemonDetails = PokemonDetails(),
    val pokemonSpeciesModel: PokemonSpeciesModel = PokemonSpeciesModel()
)