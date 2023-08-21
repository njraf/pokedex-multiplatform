package components

import Type // PokemonDetails.kt
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TypeRow(types: List<Type>) {
    Row {
        for (type in types) {
            val color: Color =
                PokemonTypes.entries.firstNotNullOf { t -> t.color.takeIf { t.typeName == type.type.name } }
            Text(
                type.type.name.uppercase(),
                modifier = Modifier.width(120.dp).padding(2.dp).background(
                    color,
                    shape = RoundedCornerShape(100.dp)
                ).padding(10.dp),
                textAlign = TextAlign.Center
            )
        } // for each type enum
    } // Row
}