import androidx.compose.ui.graphics.Color

enum class PokemonTypes(val typeName: String, val color: Color) {
    NORMAL("normal", Color(0xFFAAAA99)),
    FIRE("fire", Color(0XFFFF4422)),
    WATER("water", Color(0xFF3399FF)),
    ELECTRIC("electric", Color(0XFFFFCC33)),
    GRASS("grass", Color(0xFF5FA344)),
    ICE("ice", Color(0xFF66CCFF)),
    FIGHTING("fighting", Color(0xFFBB5544)),
    POISON("poison", Color(0xFFAA5599)),
    GROUND("ground", Color(0xFFDDBB55)),
    FLYING("flying", Color(0xFF8899FF)),
    PSYCHIC("psychic", Color(0xFFFF5599)),
    BUG("bug", Color(0xFFAABB22)),
    ROCK("rock", Color(0xFFBBAA66)),
    GHOST("ghost", Color(0xFF6666BB)),
    DRAGON("dragon", Color(0xFF7766EE)),
    DARK("dark", Color(0xFF775544)),
    STEEL("steel", Color(0xFFAAAABB)),
    FAIRY("fairy", Color(0xFFEE99EE))
}

// capitalize the first char of the string
fun String.upperFirst(): String =
    this.mapIndexed { index, c -> if (index == 0) c.uppercaseChar() else c }
        .fold("") { acc, c -> acc + c }

// capitalize the first char of each word separated by 'separator'
fun String.upperFirstWords(
    delimiter: Char, // how to split a string into words
    ignoreCase: Boolean = false, // ignore case during the split
    limit: Int = 0, // max number of words
): String {
    val newWords = mutableListOf<String>()
    this.split(delimiter, ignoreCase = ignoreCase, limit = limit).forEach { newWords.add(it.upperFirst()) }
    return newWords.reduce { acc, s -> acc + delimiter + s }
}