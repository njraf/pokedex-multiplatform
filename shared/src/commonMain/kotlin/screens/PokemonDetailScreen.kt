package screens

import components.StatBars
import PokemonDetailViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

    LaunchedEffect(nationalDexNumber) {
        pokemonDetailViewModel.getPokemonDetails(name, nationalDexNumber)
    }

    when (uiState) {
        is PokemonDetailsUiState.Loading ->
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        is PokemonDetailsUiState.Error ->
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text((uiState as PokemonDetailsUiState.Error).message)
            }
        else -> ""
    }

    if (uiState !is PokemonDetailsUiState.Success) {
        return
    }

    val successState = uiState as PokemonDetailsUiState.Success

    /// Content ///

    Column(modifier = Modifier.fillMaxSize().padding(10.dp).verticalScroll(state = rememberScrollState())) {
        if (successState.pokemonDetails.id != 0) {
            ContentImage(successState)
        }

        InfoRow(successState)

        TypeRow(successState.pokemonDetails.types)

        val mainColor =
            PokemonTypes.entries.firstNotNullOf { t -> t.color.takeIf { t.typeName == successState.pokemonDetails.types[0].type.name } }

        StatBars(successState.pokemonDetails.stats, mainColor)

        val flavorText = successState.pokemonSpeciesModel.flavorTextEntries.firstOrNull { it.language.name == "en" }?.flavorText ?: ""
        Text(
            text = flavorText.replace('\n', ' ').replace('\u000c', ' '),
            softWrap = true,
            modifier = Modifier.fillMaxWidth()
        )
    } // Column
}

@Composable
fun InfoRow(successState: PokemonDetailsUiState.Success) {
    // name, weight, height, dex number
    Row(modifier = Modifier.fillMaxWidth()) {
        // name, dex number
        Column {
            val pokemonName = if (successState.pokemonDetails.name.isNotEmpty())
                successState.pokemonDetails.name.upperFirstWords('-')
            else ""
            Text(pokemonName)
            Text("#${successState.pokemonDetails.id}")
        } // Column

        Spacer(modifier = Modifier.padding(8.dp))

        // weight, height
        Column {
            Row {
                Text("Weight ")
                Text(successState.pokemonDetails.weight.toString())
            }
            Row {
                Text("Height ")
                Text(successState.pokemonDetails.height.toString())
            }
        }
    } // Row
}

@Composable
fun ContentImage(successState: PokemonDetailsUiState.Success) {
    val image =
        asyncPainterResource(successState.pokemonDetails.sprites.other.officialArtwork.frontDefault)
    if (image is Resource.Success) {
        KamelImage(
            resource = image,
            contentDescription = successState.pokemonDetails.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(150.dp)
        )
    } else {
        Box(modifier = Modifier.size(150.dp), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}