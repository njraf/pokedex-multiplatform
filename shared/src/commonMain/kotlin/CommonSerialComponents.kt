import kotlinx.serialization.Serializable

@Serializable
data class NameAndUrl(
    val name: String = "",
    val url: String = ""
)