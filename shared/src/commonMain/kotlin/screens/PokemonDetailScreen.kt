package screens

import components.StatBars
import PokemonDetailViewModel
import Types
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import components.TypeRow

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
        // name, weight, height, dex number
        Row {
            // name, dex number, types
            Column {
                val pokemonName = if (uiState.pokemonDetails.name.isNotEmpty())
                    uiState.pokemonDetails.name.replace(
                        uiState.pokemonDetails.name[0],
                        uiState.pokemonDetails.name[0].uppercaseChar()
                    )
                else ""
                Text(pokemonName)
                Text("#${uiState.pokemonDetails.order}")

                TypeRow(uiState.pokemonDetails.types)
            } // Column

            // weight, height
            Column {
                Row {
                    Text("Weight ")
                    Text(uiState.pokemonDetails.weight.toString())
                }
                Row {
                    Text("Height ")
                    Text(uiState.pokemonDetails.height.toString())
                }
            }
        } // Row

        StatBars(uiState.pokemonDetails.stats, uiState.pokemonDetails.types[0].type)
    } // Column
}