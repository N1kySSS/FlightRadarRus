package com.ortin.flightradar.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.ortin.flightradar.MainActivity
import com.ortin.flightradar.R
import org.maplibre.android.maps.Style
import org.ramani.compose.MapLibre

@Composable
fun MapScreen(modifier: Modifier) {
    val context = LocalContext.current
    val key = context.getString(R.string.MAPS_API_KEY)

    MapLibre(
        modifier = modifier.fillMaxSize(),
        onMapClick = { MainActivity.onMapClicked() },
        styleBuilder = Style.Builder().fromUri("https://api.maptiler.com/maps/streets-v2/style.json?key=$key")
    )
}
