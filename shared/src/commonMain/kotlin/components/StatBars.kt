package components

import Stat
import TypeX
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp


@Composable
fun StatBars(stats: List<Stat>, mainType: TypeX) {
    val mainColor =
        Types.entries.firstNotNullOf { t -> t.color.takeIf { t.typeName == mainType.name } }

    LazyColumn(
        modifier = Modifier.background(
            Color(
                red = mainColor.red,
                green = mainColor.green,
                blue = mainColor.blue,
                alpha = 0.2f
            )
        )
    ) {
        items(stats) { stat ->
            Row(
                modifier = Modifier.padding(4.dp)
            ) {
                Text(stat.stat.name.replace('-', ' '), modifier = Modifier.weight(1.0f, true))
                LinearProgressIndicator(
                    progress = stat.baseStat.toFloat() / 255f,
                    color = mainColor,
                    backgroundColor = Color(
                        red = mainColor.red,
                        green = mainColor.green,
                        blue = mainColor.blue,
                        alpha = 0.5f
                    ),
                    strokeCap = StrokeCap.Round,
                    modifier = Modifier.fillMaxSize().requiredHeight(10.dp)
                        .align(Alignment.CenterVertically).weight(9.0f, true)
                )
                Box(modifier = Modifier.weight(1.0f, true)) {
                    Text(stat.baseStat.toString(),
                        modifier = Modifier.padding(4.dp).align(Alignment.CenterEnd)
                    )
                }
            } // Row
        } // items
    } // LazyColumn
}