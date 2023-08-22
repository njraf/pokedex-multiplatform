import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokedexModel( // regional pokedex
    val descriptions: List<Description> = emptyList(), // description with region and game names | ignore
    val id: Int = 0, // URL parameter | ignore
    @SerialName("is_main_series") val isMainSeries: Boolean = false, // ignore
    @SerialName("name") val regionName: String = "", // region name
    @SerialName("names") val regionNames: List<Name> = emptyList(), // region name in other languages | ignore
    @SerialName("pokemon_entries") val pokemonEntries: List<PokemonEntry> = emptyList(), // pokedex entries | main content
    val region: NameAndUrl = NameAndUrl(), // region name | ignore
    @SerialName("version_groups") val versionGroups: List<NameAndUrl> = emptyList() // game names | ignore
)

@Serializable
data class Description(
    val description: String = "",
    val language: NameAndUrl = NameAndUrl()
)

@Serializable
data class Name(
    val language: NameAndUrl = NameAndUrl(),
    val name: String = ""
)

@Serializable
data class PokemonEntry(
    @SerialName("entry_number") val entryNumber: Int = 0,
    @SerialName("pokemon_species") val pokemonSpecies: NameAndUrl = NameAndUrl()
)
