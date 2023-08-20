import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private val defaultOther = Other(DreamWorld("", ""), Home("", "", "", ""), OfficialArtwork("", ""))

private val defaultGenerationI = GenerationI(RedBlue("", "", "", "", "", ""), Yellow("", "", "", "", "", ""))

private val defaultGenerationIi = GenerationIi(Crystal("", "", "", "", "", "", "", ""), Gold("", "", "", "", ""), Silver("", "", "", "", ""))
private val defaultGenerationIii = GenerationIii(Emerald("", ""), FireredLeafgreen("", "", "", ""), RubySapphire("", "", "", ""))
private val defaultGenerationIv = GenerationIv(DiamondPearl("", "", "", "", "", "", "", ""), HeartgoldSoulsilver("", "", "", "", "", "", "", ""), Platinum("", "", "", "", "", "", "", ""))
private val defaultGenerationV = GenerationV(BlackWhite(Animated("", "", "", "", "", "", "", ""), "", "", "", "", "", "", "", ""))
private val defaultGenerationVi = GenerationVi(OmegarubyAlphasapphire("", "", "", ""), XY("", "", "", ""))
private val defaultGenerationVii = GenerationVii(Icons("", ""), UltraSunUltraMoon("", "", "", ""))
private val defaultGenerationViii = GenerationViii(Icons("", ""))

private val defaultVersions = Versions(
    defaultGenerationI,
    defaultGenerationIi,
    defaultGenerationIii,
    defaultGenerationIv,
    defaultGenerationV,
    defaultGenerationVi,
    defaultGenerationVii,
    defaultGenerationViii
)

@Serializable
data class PokemonDetails(
    val abilities: List<Ability>? = emptyList(),
    @SerialName("base_experience") val baseExperience: Int = 0,
    val forms: List<Form> = emptyList(),
    @SerialName("game_indices") val gameIndices: List<GameIndice> = emptyList(),
    val height: Int = 0,
    @SerialName("held_items") val heldItems: List<HeldItem> = emptyList(),
    val id: Int = 0,
    @SerialName("is_default") val isDefault: Boolean = true,
    @SerialName("location_area_encounters") val locationAreaEncounters: String = "",
    val moves: List<Move> = emptyList(),
    val name: String = "",
    val order: Int = 0,
    @SerialName("past_types") val pastTypes: List<PastType> = emptyList(),
    val species: Species = Species("", ""),
    val sprites: Sprites = Sprites("", "", "", "", "", "", "", "", defaultOther, defaultVersions),
    val stats: List<Stat> = emptyList(),
    val types: List<Type> = emptyList(),
    val weight: Int = 0
)

@Serializable
data class Ability(
    val ability: AbilityX = AbilityX("", ""),
    val is_hidden: Boolean = false,
    val slot: Int = 0
)

@Serializable
data class Form(
    val name: String = "",
    val url: String = ""
)

@Serializable
data class GameIndice(
    val game_index: Int = 0,
    val version: Version = Version("", "")
)

@Serializable
data class HeldItem(
    val item: Item = Item("", ""),
    val version_details: List<VersionDetail> = emptyList()
)

@Serializable
data class Move(
    val move: MoveX = MoveX("", ""),
    val version_group_details: List<VersionGroupDetail> = emptyList()
)

@Serializable
data class PastType(
    val generation: Generation = Generation("", ""),
    val types: List<Type> = emptyList()
)

@Serializable
data class Species(
    val name: String = "",
    val url: String = ""
)

@Serializable
data class Sprites(
    val back_default: String = "",
    val back_female: String = "",
    val back_shiny: String = "",
    val back_shiny_female: String = "",
    val front_default: String = "",
    val front_female: String = "",
    val front_shiny: String = "",
    val front_shiny_female: String = "",
    val other: Other = defaultOther,
    val versions: Versions = defaultVersions
)

@Serializable
data class Stat(
    val base_stat: Int = 0,
    val effort: Int = 0,
    val stat: StatX = StatX("", "")
)

@Serializable
data class AbilityX(
    val name: String = "",
    val url: String = ""
)

@Serializable
data class Version(
    val name: String = "",
    val url: String = ""
)

@Serializable
data class Item(
    val name: String = "",
    val url: String = ""
)

@Serializable
data class VersionDetail(
    val rarity: Int = 0,
    val version: Version = Version("", "")
)

@Serializable
data class MoveX(
    val name: String = "",
    val url: String = ""
)

@Serializable
data class VersionGroupDetail(
    @SerialName("level_learned_at") val levelLearnedAt: Int = 0,
    val move_learn_method: MoveLearnMethod = MoveLearnMethod("", ""),
    val version_group: VersionGroup = VersionGroup("", "")
)

@Serializable
data class MoveLearnMethod(
    val name: String = "",
    val url: String = ""
)

/*@Serializable
data class VersionGroup(
    val name: String = "",
    val url: String = ""
)*/

@Serializable
data class Generation(
    val name: String = "",
    val url: String = ""
)

@Serializable
data class Type(
    val slot: Int = 0,
    val type: TypeX = TypeX("", "")
)

@Serializable
data class TypeX(
    val name: String = "",
    val url: String = ""
)

@Serializable
data class Other(
    val dream_world: DreamWorld = DreamWorld("", ""),
    val home: Home = Home("", "", "", ""),
    @SerialName("official-artwork") val officialArtwork: OfficialArtwork = OfficialArtwork("", "")
)

@Serializable
data class Versions(
    @SerialName("generation-i") val generation1: GenerationI = defaultGenerationI,
    @SerialName("generation-ii") val generation2: GenerationIi = defaultGenerationIi,
    @SerialName("generation-iii") val generation3: GenerationIii = defaultGenerationIii,
    @SerialName("generation-iv") val generation4: GenerationIv = defaultGenerationIv,
    @SerialName("generation-v") val generation5: GenerationV = defaultGenerationV,
    @SerialName("generation-vi") val generation6: GenerationVi = defaultGenerationVi,
    @SerialName("generation-vii") val generation7: GenerationVii = defaultGenerationVii,
    @SerialName("generation-viii") val generation8: GenerationViii = defaultGenerationViii
)

@Serializable
data class DreamWorld(
    val front_default: String = "",
    val front_female: String = ""
)

@Serializable
data class Home(
    val front_default: String = "",
    val front_female: String = "",
    val front_shiny: String = "",
    val front_shiny_female: String = ""
)

@Serializable
data class OfficialArtwork(
    val front_default: String = "",
    val front_shiny: String
)

@Serializable
data class GenerationI(
    @SerialName("red-blue") val redBlue: RedBlue = RedBlue("", "", "", "", "", ""),
    val yellow: Yellow = Yellow("", "", "", "", "", "")
)

@Serializable
data class GenerationIi(
    val crystal: Crystal = Crystal("", "", "", "", "", ""),
    val gold: Gold = Gold("", "", "", "", ""),
    val silver: Silver = Silver("", "", "", "", "")
)

@Serializable
data class GenerationIii(
    val emerald: Emerald = Emerald("", ""),
    @SerialName("firered-leafgreen") val fireRedLeafGreen: FireredLeafgreen = FireredLeafgreen("", "", "", ""),
    @SerialName("ruby-sapphire") val rubySapphire: RubySapphire = RubySapphire("", "", "", "")
)

@Serializable
data class GenerationIv(
    @SerialName("diamond-pearl") val diamondPearl: DiamondPearl = DiamondPearl("", "", "", "", "", ""),
    @SerialName("heartgold-soulsilver") val heartgoldSoulsilver: HeartgoldSoulsilver = HeartgoldSoulsilver("", "", "", "", "", ""),
    val platinum: Platinum = Platinum("", "", "", "", "", "")
)

@Serializable
data class GenerationV(
    @SerialName("black-white") val blackWhite: BlackWhite = BlackWhite(Animated("", ""), "", "", "", "", "")
)

@Serializable
data class GenerationVi(
    @SerialName("omegaruby-alphasapphire") val omegarubyAlphasapphire: OmegarubyAlphasapphire = OmegarubyAlphasapphire("", "", "", ""),
    @SerialName("x-y") val xy: XY = XY("", "", "", "")
)

@Serializable
data class GenerationVii(
    val icons: Icons?,
    @SerialName("ultra-sun-ultra-moon") val ultraSunUltraMoon: UltraSunUltraMoon = UltraSunUltraMoon("", "", "", "")
)

@Serializable
data class GenerationViii(
    val icons: Icons = Icons("", "")
)

@Serializable
data class RedBlue(
    val back_default: String = "",
    val back_gray: String = "",
    val back_transparent: String = "",
    val front_default: String = "",
    val front_gray: String = "",
    val front_transparent: String = ""
)

@Serializable
data class Yellow(
    val back_default: String = "",
    val back_gray: String = "",
    val back_transparent: String = "",
    val front_default: String = "",
    val front_gray: String = "",
    val front_transparent: String = ""
)

@Serializable
data class Crystal(
    val back_default: String = "",
    val back_shiny: String = "",
    val back_shiny_transparent: String = "",
    val back_transparent: String = "",
    val front_default: String = "",
    val front_shiny: String = "",
    val front_shiny_transparent: String = "",
    val front_transparent: String = ""
)

@Serializable
data class Gold(
    val back_default: String = "",
    val back_shiny: String = "",
    val front_default: String = "",
    val front_shiny: String = "",
    val front_transparent: String = ""
)

@Serializable
data class Silver(
    val back_default: String = "",
    val back_shiny: String = "",
    val front_default: String = "",
    val front_shiny: String = "",
    val front_transparent: String = ""
)

@Serializable
data class Emerald(
    val front_default: String = "",
    val front_shiny: String = ""
)

@Serializable
data class FireredLeafgreen(
    val back_default: String = "",
    val back_shiny: String = "",
    val front_default: String = "",
    val front_shiny: String = ""
)

@Serializable
data class RubySapphire(
    val back_default: String = "",
    val back_shiny: String = "",
    val front_default: String = "",
    val front_shiny: String = ""
)

@Serializable
data class DiamondPearl(
    val back_default: String = "",
    val back_female: String = "",
    val back_shiny: String = "",
    val back_shiny_female: String = "",
    val front_default: String = "",
    val front_female: String = "",
    val front_shiny: String = "",
    val front_shiny_female: String = ""
)

@Serializable
data class HeartgoldSoulsilver(
    val back_default: String = "",
    val back_female: String = "",
    val back_shiny: String = "",
    val back_shiny_female: String = "",
    val front_default: String = "",
    val front_female: String = "",
    val front_shiny: String = "",
    val front_shiny_female: String = ""
)

@Serializable
data class Platinum(
    val back_default: String = "",
    val back_female: String = "",
    val back_shiny: String = "",
    val back_shiny_female: String = "",
    val front_default: String = "",
    val front_female: String = "",
    val front_shiny: String = "",
    val front_shiny_female: String = ""
)

@Serializable
data class BlackWhite(
    val animated: Animated?,
    val back_default: String = "",
    val back_female: String = "",
    val back_shiny: String = "",
    val back_shiny_female: String = "",
    val front_default: String = "",
    val front_female: String = "",
    val front_shiny: String = "",
    val front_shiny_female: String = ""
)

@Serializable
data class Animated(
    val back_default: String = "",
    val back_female: String = "",
    val back_shiny: String = "",
    val back_shiny_female: String = "",
    val front_default: String = "",
    val front_female: String = "",
    val front_shiny: String = "",
    val front_shiny_female: String = ""
)

@Serializable
data class OmegarubyAlphasapphire(
    val front_default: String = "",
    val front_female: String = "",
    val front_shiny: String = "",
    val front_shiny_female: String = ""
)

@Serializable
data class XY(
    val front_default: String = "",
    val front_female: String = "",
    val front_shiny: String = "",
    val front_shiny_female: String = ""
)

@Serializable
data class Icons(
    val front_default: String = "",
    val front_female: String = ""
)

@Serializable
data class UltraSunUltraMoon(
    val front_default: String = "",
    val front_female: String = "",
    val front_shiny: String = "",
    val front_shiny_female: String = ""
)

@Serializable
data class StatX(
    val name: String = "",
    val url: String = ""
)