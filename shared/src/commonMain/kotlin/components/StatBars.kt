package components

import Stat
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import upperFirstWords


@Composable
fun StatBars(stats: List<Stat>, mainColor: Color, modifier: Modifier) {
    Column(modifier = modifier) {
        for (stat in stats) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(4.dp)
            ) {
                Text(stat.stat.name.upperFirstWords('-').replace('-', '\n'), modifier = Modifier.width(80.dp))
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
                    modifier = Modifier.requiredHeight(10.dp)
                        .align(Alignment.CenterVertically).weight(1f)
                )
                Text(
                    stat.baseStat.toString(),
                    modifier = Modifier.padding(4.dp).width(40.dp),
                    textAlign = TextAlign.End
                )
            } // Row
        } // for each stat
    } // LazyColumn
}