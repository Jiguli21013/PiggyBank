package com.yanchelenko.piggybank.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun DrawRectangle(
    rect: Rect?,
) {
    if (rect == null) return
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRect(
            color = Color.Green,
            topLeft = Offset(x = rect.left, y = rect.top),
            size = Size(width = rect.width, height = rect.height),
            style = Stroke(width = 5f) //todo from constants
        )
    }
}
