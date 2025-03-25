package com.ortin.flightradar.presentation.component.topsheet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTopSheet(
    isVisible: Boolean,
    content: @Composable () -> Unit,
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val sheetHeight = screenHeight * 3 / 4

    AnimatedVisibility(
        visible = isVisible,
        exit = slideOutVertically(animationSpec = tween(800)) { -it },
        enter = slideInVertically(animationSpec = tween(800)) { -it }
    ) {
        Box(
            modifier = Modifier
                .padding(top = 110.dp)
                .height(sheetHeight)
                .background(
                    Color.Black.copy(alpha = 0.4f),
                    RoundedCornerShape(
                        bottomStart = 12.dp,
                        bottomEnd = 12.dp
                    )
                )
                .fillMaxWidth()
        ) {
            content()
        }
    }
}

@Preview
@Composable
fun CustomTopSheetPreview() {
    var isSheetVisible by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxSize()) {
        Button(
            onClick = { isSheetVisible = !isSheetVisible },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text("Открыть/Закрыть TopSheet")
        }
        CustomTopSheet(isVisible = isSheetVisible) {
            Column(Modifier.padding(16.dp)) {
                Text("Top Sheet", fontSize = 20.sp)
            }
        }
    }
}
