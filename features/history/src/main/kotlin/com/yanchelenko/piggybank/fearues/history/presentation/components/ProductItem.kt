package com.yanchelenko.piggybank.fearues.history.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.yanchelenko.piggybank.common.ui_models.ProductUiModel
import com.yanchelenko.piggybank.fearues.history.presentation.preview.ProductPreviewProvider
import com.yanchelenko.piggybank.fearues.history.presentation.state.HistoryEvent
import com.yanchelenko.piggynank.core.ui.components.ConfirmDeleteDialog
import com.yanchelenko.piggynank.core.ui.theme.PiggyBankTheme

@Composable
internal fun ProductItem(
    product: ProductUiModel,
    modifier: Modifier = Modifier,
    onEvent: (HistoryEvent) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) } //todo логику диалога вынести выше ProductItem'a

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .clickable { onEvent.invoke(HistoryEvent.OnProductClicked(product = product)) }
            .padding(12.dp)
    ) {
        Column {
            ProductField(label = "Название", value = product.productName)
            ProductField(label = "ID", value = product.productId.toString())
            ProductField(label = "Штрихкод", value = product.barcode.orEmpty())
            ProductField(label = "Цена", value = "${product.price} ₽")
            ProductField(label = "Вес", value = "${product.weight} г")
            ProductField(label = "Цена за кг", value = "${product.pricePerKg} ₽")
        }

        IconButton(
            onClick = { showDialog = true },
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Удалить продукт",
                tint = Color.Red
            )
        }

        if (showDialog) {
            ConfirmDeleteDialog(
                onConfirm = {
                    showDialog = false
                    onEvent.invoke(HistoryEvent.OnProductDeleted(product = product))
                },
                onDismiss = { showDialog = false }
            )
        }
    }
}

@Preview
@Composable
fun ProductItemPreview(
    @PreviewParameter(ProductPreviewProvider::class)
    product: ProductUiModel
) {
    PiggyBankTheme {
        ProductItem(
            product = product,
            onEvent = {}
        )
    }
}

