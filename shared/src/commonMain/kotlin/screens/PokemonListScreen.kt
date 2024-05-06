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
import androidx.compose.ui.unit.sp
import upperFirstWords

@Composable
fun PokemonListScreen(
    pokemonListViewModel: PokemonListViewModel,
    onDetailNavigate: (String, Int) -> Unit,
) {
    val uiState by pokemonListViewModel.uiState.collectAsState()

    Column {
        LazyRow {
            items(Regions.entries) { region ->
                Button(onClick = { pokemonListViewModel.getNames(region) }) { Text(region.name) }
            }
        }

        when (uiState) {
            is PokemonUiState.Loading ->
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }

            is PokemonUiState.Error ->
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text((uiState as PokemonUiState.Error).message)
                }

            else -> ""
        }

        if (uiState !is PokemonUiState.Success) {
            return
        }

        val successState = uiState as PokemonUiState.Success

        /// Content ///

        LazyColumn {
            items(successState.pokemonNames) { entry ->
                Text(
                    text = "${entry.entryNumber}. ${entry.pokemonSpecies.name.upperFirstWords('-')}",
                    fontSize = 22.sp,
                    modifier = Modifier.fillMaxSize().clickable {
                        onDetailNavigate(
                            entry.pokemonSpecies.name,
                            pokemonListViewModel.nationalDex.firstOrNull { it.pokemonSpecies.name == entry.pokemonSpecies.name }?.entryNumber ?: 0
                        )
                    }
                )
            }
        }
    }
}


