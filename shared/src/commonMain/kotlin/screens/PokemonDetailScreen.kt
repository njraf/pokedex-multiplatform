package screens

import PokemonDetailViewModel
import Types
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun PokemonDetailScreen(pokemonDetailViewModel: PokemonDetailViewModel, name: String) {
    val uiState by pokemonDetailViewModel.uiState.collectAsState()

    if ((uiState.pokemonDetails.name.lowercase() != name)) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        pokemonDetailViewModel.getPokemonDetails(name)
        return
    }

    val mainColor =
        Types.entries.firstNotNullOf { t -> t.color.takeIf { t.typeName == uiState.pokemonDetails.types[0].type.name } }

    Column(modifier = Modifier.padding(10.dp)) {
        val pokemonName = if (uiState.pokemonDetails.name.isNotEmpty())
            uiState.pokemonDetails.name.replace(
                uiState.pokemonDetails.name[0],
                uiState.pokemonDetails.name[0].uppercaseChar()
            )
        else ""
        Text(pokemonName)
        Row {
            for (type in uiState.pokemonDetails.types) {
                val color: Color =
                    Types.entries.firstNotNullOf { t -> t.color.takeIf { t.typeName == type.type.name } }
                Text(type.type.name.uppercase(), modifier = Modifier.background(color).padding(10.dp))
            } // for each type enum
        } // Row
        LazyColumn {
            items(uiState.pokemonDetails.stats) { stat ->
                Row(
                    modifier = Modifier.padding(vertical = 4.dp).background(
                        Color(
                            red = mainColor.red,
                            green = mainColor.green,
                            blue = mainColor.blue,
                            alpha = 0.2f
                        )
                    ).padding(4.dp)
                ) {
                    Text(stat.stat.name.replace('-', ' '), modifier = Modifier.weight(1.0f, true))
                    LinearProgressIndicator(
                        progress = stat.base_stat.toFloat() / 255f,
                        color = mainColor,
                        backgroundColor = Color(
                            red = mainColor.red,
                            green = mainColor.green,
                            blue = mainColor.blue,
                            alpha = 0.5f
                        ),
                        strokeCap = StrokeCap.Round,
                        modifier = Modifier.fillMaxSize().requiredHeight(10.dp)
                            .align(Alignment.CenterVertically).weight(4.0f, true)
                    )
                } // Row
            } // items
        } // LaxyColumn
    } // Column
}