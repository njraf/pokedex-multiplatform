import kotlinx.serialization.Serializable

@Serializable
data class Pokedex( // regional pokedex
    val descriptions: List<Description>, // description with region and game names | ignore
    val id: Int, // URL parameter | ignore
    val is_main_series: Boolean, // ignore
    val name: String, // region name
    val names: List<Name>, // region name in other languages | ignore
    val pokemon_entries: List<PokemonEntry>, // pokedex entries | main content
    val region: Region, // region name | ignore
    val version_groups: List<VersionGroup> // game names | ignore
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
    val entry_number: Int,
    val pokemon_species: PokemonSpecies
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