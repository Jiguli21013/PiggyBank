package com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.yanchelenko.piggybank.modules.features.scanner.scanner_impl.R
import com.yanchelenko.piggynank.core.ui.theme.Dimens.PaddingLarge
import com.yanchelenko.piggynank.core.ui.theme.Dimens.SpacingLarge

@Composable
fun CameraPermissionDeniedContent(
    onOpenSettings: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = PaddingLarge),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.scanner_permission_text))
        Spacer(modifier = Modifier.height(height = SpacingLarge))
        Button(onClick = onOpenSettings) { Text(text = stringResource(id = R.string.scanner_open_settings)) }
    }
}
