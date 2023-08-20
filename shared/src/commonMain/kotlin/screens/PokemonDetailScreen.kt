package screens

import PokemonDetailViewModel
import Types
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    Column(modifier = Modifier.padding(10.dp)) {
        Text(uiState.pokemonDetails.name)
        Row {
            for (type in uiState.pokemonDetails.types) {
                val color: Color = Types.entries.firstNotNullOf { t -> t.color.takeIf { t.typeName == type.type.name } }
                Text(type.type.name, modifier = Modifier.background(color).padding(10.dp))
            } // for each type enum
        } // Row
    } // Column
}