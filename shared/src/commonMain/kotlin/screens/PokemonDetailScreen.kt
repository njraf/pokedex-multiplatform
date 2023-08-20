package screens

import PokemonDetailViewModel
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
import androidx.compose.ui.unit.dp

@Composable
fun PokemonDetailScreen(pokemonDetailViewModel: PokemonDetailViewModel, name: String) {
    val uiState by pokemonDetailViewModel.uiState.collectAsState()

    if ((uiState.pokemonDetails?.name?.lowercase() != name)) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        pokemonDetailViewModel.getPokemonDetails(name)
        return
    }

    Column(modifier = Modifier.padding(10.dp)) {
        Text(uiState.pokemonDetails?.name ?: "ERROR")
        Row {
            for (type in uiState.pokemonDetails?.types ?: emptyList()) {
                Text(type.type?.name ?: "ERROR", modifier = Modifier.padding(10.dp))
            }
        }
    }
}