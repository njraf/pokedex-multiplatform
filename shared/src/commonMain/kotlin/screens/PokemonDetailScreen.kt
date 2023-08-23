package screens

import components.StatBars
import PokemonDetailViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import components.TypeRow
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import upperFirstWords

@Composable
fun PokemonDetailScreen(pokemonDetailViewModel: PokemonDetailViewModel, name: String, nationalDexNumber: Int) {
    val uiState by pokemonDetailViewModel.uiState.collectAsState()

    if ((uiState.pokemonDetails.name.lowercase() != name && name.isNotEmpty())) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        pokemonDetailViewModel.getPokemonDetails(name)
        return
    }

    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
        val image =
            asyncPainterResource(uiState.pokemonDetails.sprites.other.officialArtwork.frontDefault)
        if (image is Resource.Success) {
            KamelImage(
                resource = image,
                contentDescription = uiState.pokemonDetails.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(150.dp)
            )
        } else {
            Box(modifier = Modifier.size(150.dp), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        // name, weight, height, dex number
        Row(modifier = Modifier.fillMaxWidth()) {
            // name, dex number, types
            Column {
                val pokemonName = if (uiState.pokemonDetails.name.isNotEmpty())
                    uiState.pokemonDetails.name.upperFirstWords('-')
                else ""
                Text(pokemonName)
                Text("#${if (name.isNotEmpty()) nationalDexNumber else 0}")

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

        val mainColor =
            PokemonTypes.entries.firstNotNullOf { t -> t.color.takeIf { t.typeName == uiState.pokemonDetails.types[0].type.name } }

        StatBars(
            uiState.pokemonDetails.stats, mainColor, modifier = Modifier.fillMaxWidth().background(
                Color(
                    red = mainColor.red,
                    green = mainColor.green,
                    blue = mainColor.blue,
                    alpha = 0.2f
                )
            )
        )

        val flavorText = uiState.pokemonSpeciesModel.flavorTextEntries.firstOrNull { it.language.name == "en" }?.flavorText ?: ""
        Text(
            text = flavorText.replace('\n', ' ').replace('\u000c', ' '),
            softWrap = true,
            modifier = Modifier.fillMaxWidth()
        )
    } // Column
}