package dev.anand.synchronossweatherapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import dev.anand.synchronossweatherapp.domain.CurrentWeather

@ExperimentalMaterial3Api
@Composable
fun WeatherCard(
   weather: CurrentWeather,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            //.border(1.dp, Color.Red, RectangleShape)
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = weather.name,
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "Updated at ${weather.dt}",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                //.border(1.dp, Color.Red, RectangleShape)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val superscript = SpanStyle(
                baselineShift = BaselineShift.Superscript,
                fontSize = 20.sp, // font size of superscript
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = buildAnnotatedString {

                    append(weather.temp.toString())
                    withStyle(superscript) {
                        append("o")
                    }
                    append(" C")
                },
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,

                    )

            )
            Image(
                painter = rememberAsyncImagePainter(
                    model = "https://openweathermap.org/img/wn/${weather.icon}@2x.png"
                ),
                contentDescription = null,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.large)
                    .fillMaxWidth()
                    .aspectRatio(3f / 2f)
            )

            Text(
                text = weather.description,
                style = MaterialTheme.typography.headlineLarge,
            )
        }
    }


}