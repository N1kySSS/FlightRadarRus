package com.ortin.flightradar.presentation.component.topsheet

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ortin.flightradar.ui.theme.Background

@Composable
fun CustomTopSheet(
    isVisible: Boolean,
    content: @Composable () -> Unit,
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val sheetHeight = screenHeight * 3 / 4

    val animatedHeight by animateDpAsState(
        targetValue = if (isVisible) sheetHeight else 0.dp,
        animationSpec = tween(durationMillis = 800),
        label = "SheetHeight"
    )

    Box(
        modifier = Modifier
            .padding(top = 110.dp)
            .height(animatedHeight)
            .fillMaxWidth()
            .background(
                Background,
                RoundedCornerShape(
                    bottomStart = 12.dp,
                    bottomEnd = 12.dp
                )
            )
    ) {
        if (animatedHeight > 0.dp) {
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
