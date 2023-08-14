package screens

import PokemonListViewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun PokemonListScreen() {
    val pokemonListViewModel = PokemonListViewModel()
    val uiState by pokemonListViewModel.uiState.collectAsState()

    LazyColumn {
        items(uiState.pokemonNames) {entry ->
            Text("${entry.entry_number}. ${entry.pokemon_species.name}")
        }
    }


}

