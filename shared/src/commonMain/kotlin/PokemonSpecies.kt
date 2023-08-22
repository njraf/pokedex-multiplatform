import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpeciesModel(
    @SerialName("base_happiness") val baseHappiness: Int = 0,
    @SerialName("capture_rate") val captureRate: Int = 0,
    val color: NameAndUrl = NameAndUrl(),
    @SerialName("egg_groups") val eggGroups: List<NameAndUrl> = emptyList(),
    @SerialName("evolution_chain") val evolutionChain: EvolutionChain = EvolutionChain(),
    @SerialName("evolves_from_species") val evolvesFromSpecies: NameAndUrl = NameAndUrl(),
    @SerialName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntry> = emptyList(),
    @SerialName("form_descriptions") val formDescriptions: List<String> = emptyList(), //TODO: json is empty array; find actual value
    @SerialName("forms_switchable") val formsSwitchable: Boolean = false,
    @SerialName("gender_rate") val genderRate: Int = 0,
    val genera: List<Genera> = emptyList(),
    val generation: NameAndUrl = NameAndUrl(),
    @SerialName("growth_rate") val growthRate: NameAndUrl = NameAndUrl(),
    val habitat: NameAndUrl = NameAndUrl(),
    @SerialName("has_gender_differences") val hasGenderDifferences: Boolean = false,
    @SerialName("hatch_counter") val hatchCounter: Int = 0,
    val id: Int = 0,
    @SerialName("is_baby") val isBaby: Boolean = false,
    @SerialName("is_legendary") val isLegendary: Boolean = false,
    @SerialName("is_mythical") val isMythical: Boolean = false,
    val name: String = "",
    val names: List<Name> = emptyList(),
    val order: Int = 0,
    @SerialName("pal_park_encounters") val palParkEncounters: List<PalParkEncounter> = emptyList(),
    @SerialName("pokedex_numbers") val pokedexNumbers: List<PokedexNumber> = emptyList(),
    val shape: NameAndUrl = NameAndUrl(),
    val varieties: List<Variety> = emptyList()
)

@Serializable
data class EvolutionChain(
    val url: String = ""
)

@Serializable
data class FlavorTextEntry(
    @SerialName("flavor_text") val flavorText: String = "",
    val language: NameAndUrl = NameAndUrl(),
    val version: NameAndUrl = NameAndUrl()
)

@Serializable
data class Genera(
    val genus: String = "",
    val language: NameAndUrl = NameAndUrl()
)

@Serializable
data class PalParkEncounter(
    val area: NameAndUrl = NameAndUrl(),
    @SerialName("base_score") val baseScore: Int = 0,
    val rate: Int = 0
)

@Serializable
data class PokedexNumber(
    @SerialName("entry_number") val entryNumber: Int = 0,
    val pokedex: NameAndUrl = NameAndUrl()
)

@Serializable
data class Variety(
    @SerialName("is_default") val isDefault: Boolean = false,
    val pokemon: NameAndUrl = NameAndUrl()
)

