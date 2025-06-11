package com.yanchelenko.piggybank.fearues.history.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yanchelenko.piggybank.common.extensions.formatAsHeader
import kotlinx.datetime.LocalDate

@Composable
fun DateHeader(date: LocalDate) {
    Text(
        text = remember(date) { date.formatAsHeader() },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp),
        style = MaterialTheme.typography.titleMedium
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDateHeader() {
    DateHeader(
        date = LocalDate(
            year = 2025,
            monthNumber = 6,
            dayOfMonth = 6
        )
    )
}
