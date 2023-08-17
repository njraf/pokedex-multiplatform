import androidx.compose.ui.window.Popup
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

enum class Regions(val code: String) {
    KANTO("2"),
    JOHTO("3"),
    HOENN("4"),
    SINNOH("5"),
    UNOVA("8"),
    KALOS("12"),
    ALOLA("16"),
    GALAR("27"),
    PALDEA("31"),
    //NATIONAL("1")
}

class PokemonDataSource(private val client: HttpClient) {

    suspend fun fetchPokemon(region: Regions): List<PokemonEntry> {
        return try {
            val numRequests = if (region == Regions.KALOS) {
                3
            } else {
                1
            }

            val entries: MutableList<PokemonEntry> = emptyList<PokemonEntry>().toMutableList()

            for (regionCode in (region.code.toInt() until (region.code.toInt() + numRequests))) {
                val pokedex =
                    client.get("https://pokeapi.co/api/v2/pokedex/$regionCode/").body<Pokedex>()
                entries.addAll(pokedex.pokemon_entries)
            }
            entries
        } catch (e: Exception) {
            println("Error: " + e.message)
            emptyList()
        }
    }
}