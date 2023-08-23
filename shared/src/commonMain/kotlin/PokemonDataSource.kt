import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

private const val BASE_URL = "https://pokeapi.co/api/v2"

class PokemonDataSource(private val client: HttpClient) {

    suspend fun fetchPokemon(region: Regions): List<PokemonEntry> {
        return withContext(Dispatchers.IO) {
            val numRequests = if (region == Regions.KALOS) {
                3
            } else {
                1
            }

            val entries: MutableList<PokemonEntry> = emptyList<PokemonEntry>().toMutableList()
            for (regionCode in (region.code.toInt() until (region.code.toInt() + numRequests))) {
                val pokedex =
                    client.get("$BASE_URL/pokedex/$regionCode/").body<PokedexModel>()
                entries.addAll(pokedex.pokemonEntries)
            }
            entries
        } // withContext
    }

    suspend fun fetchPokemonDetails(name: String): PokemonDetails {
        return withContext(Dispatchers.IO) {
            client.get("$BASE_URL/pokemon/$name/").body<PokemonDetails>()
        }
    }

    suspend fun fetchPokemonSpecies(name: String): PokemonSpeciesModel {
        return withContext(Dispatchers.IO) {
            client.get("$BASE_URL/pokemon-species/$name/").body<PokemonSpeciesModel>()
        }
    }
}