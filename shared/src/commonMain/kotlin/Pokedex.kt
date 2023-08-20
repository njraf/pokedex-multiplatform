import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokedex( // regional pokedex
    val descriptions: List<Description>, // description with region and game names | ignore
    val id: Int, // URL parameter | ignore
    @SerialName("is_main_series") val isMainSeries: Boolean, // ignore
    @SerialName("name") val regionName: String, // region name
    @SerialName("names") val regionNames: List<Name>, // region name in other languages | ignore
    @SerialName("pokemon_entries") val pokemonEntries: List<PokemonEntry>, // pokedex entries | main content
    val region: Region, // region name | ignore
    @SerialName("version_groups") val versionGroups: List<VersionGroup> // game names | ignore
)

@Serializable
data class Description(
    val description: String,
    val language: Language
)

@Serializable
data class Name(
    val language: Language,
    val name: String
)

@Serializable
data class PokemonEntry(
    @SerialName("entry_number") val entryNumber: Int,
    @SerialName("pokemon_species") val pokemonSpecies: PokemonSpecies
)

@Serializable
data class Region(
    val name: String,
    val url: String
)

@Serializable
data class VersionGroup(
    val name: String,
    val url: String
)

@Serializable
data class Language(
    val name: String,
    val url: String
)

@Serializable
data class PokemonSpecies(
    val name: String,
    val url: String
)