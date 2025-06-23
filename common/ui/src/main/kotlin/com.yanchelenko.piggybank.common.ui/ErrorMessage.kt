package com.yanchelenko.piggybank.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.yanchelenko.piggynank.core.ui.theme.Dimens.PaddingSmall
import com.yanchelenko.piggynank.core.ui.theme.Dimens.SpacingSmall

@Composable
fun ErrorMessage(
    message: String,
    modifier: Modifier = Modifier,
    contentBelow: @Composable (() -> Unit)? = null
) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.error)
                .padding(PaddingSmall),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = message,
                color = MaterialTheme.colorScheme.onError,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        contentBelow?.let {
            Spacer(modifier = Modifier.height(SpacingSmall))
            it()
        }
    }
}
