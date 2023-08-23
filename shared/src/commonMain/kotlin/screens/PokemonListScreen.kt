package screens

import PokemonListViewModel
import Regions
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import upperFirstWords

@Composable
fun PokemonListScreen(pokemonListViewModel: PokemonListViewModel, onDetailNavigate: (String, Int) -> Unit) {
    val uiState by pokemonListViewModel.uiState.collectAsState()

    if (uiState.pokemonNames.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        pokemonListViewModel.getNames(Regions.KANTO)
        return
    }

    Column {
        LazyRow {
            items(Regions.entries) { region ->
                Button(onClick = { pokemonListViewModel.getNames(region) }) { Text(region.name) }
            }
        }
        LazyColumn {
            items(uiState.pokemonNames) { entry ->
                Text(
                    text = "${entry.entryNumber}. ${entry.pokemonSpecies.name.upperFirstWords('-')}",
                    modifier = Modifier.clickable { onDetailNavigate(entry.pokemonSpecies.name, pokemonListViewModel.nationalDex.first { it.pokemonSpecies.name == entry.pokemonSpecies.name }.entryNumber) }
                )
            }
        }
    }
}


