package screens

import PokemonListViewModel
import Regions
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PokemonListScreen(pokemonListViewModel: PokemonListViewModel) {
    val uiState by pokemonListViewModel.uiState.collectAsState()

    if (uiState.pokemonNames.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    Column {
        Row {
            for (region in Regions.entries) {
                Button(onClick = { pokemonListViewModel.getNames(region) }) { Text(region.name) }
            }
        }
        LazyColumn {
            items(uiState.pokemonNames) { entry ->
                Text("${entry.entry_number}. ${entry.pokemon_species.name}")
            }
        }
    }
}


