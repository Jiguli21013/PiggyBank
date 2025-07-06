package com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.theapache64.rebugger.Rebugger
import com.yanchelenko.piggybank.modules.base.ui_kit.animations.AnimationDurations.LONG
import com.yanchelenko.piggybank.modules.base.ui_kit.animations.AnimationDurations.MEDIUM
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.models.ListItem
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.preview.ListItemPreviewProvider
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.state.HistoryEvent
import com.yanchelenko.piggynank.core.ui.theme.Dimens.PaddingMedium

@Composable
internal fun HistoryList(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<ListItem>,
    onEvent: (HistoryEvent) -> Unit,
) {
    Rebugger(
        trackMap = mapOf(
            "itemCount" to items.itemCount,
            "loadState.refresh" to items.loadState.refresh::class.simpleName,
            "loadState.append" to items.loadState.append::class.simpleName,
            "snapshotHash" to items.itemSnapshotList.items.hashCode()
        ),
        composableName = "HistoryList"
    )

    LazyColumn(modifier = modifier) {
        items(
            count = items.itemCount,
            key = { index ->
                when (val item = items[index]) {
                    is ListItem.DateHeaderUiModel -> "header_${item.date}"
                    is ListItem.ProductItemUiModel -> "product_${item.product.productId}"
                    else -> "placeholder_$index"
                }
            }
        ) { index ->
            when (val item = items[index]) {
                is ListItem.DateHeaderUiModel -> DateHeader(
                    date = item.date,
                    modifier = modifier.animateItem(
                        placementSpec = tween(durationMillis = LONG)
                    )
                )
                is ListItem.ProductItemUiModel -> ProductItem(
                    product = item.product,
                    onEvent = onEvent,
                    modifier = modifier.animateItem(
                        fadeInSpec = tween(durationMillis = MEDIUM),
                        placementSpec = tween(durationMillis = LONG),
                        fadeOutSpec = tween(durationMillis = MEDIUM)
                    )
                )
                null -> {}
            }
        }

        when (val append = items.loadState.append) {
            is LoadState.Loading -> item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = PaddingMedium)
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                )
            }

            is LoadState.Error -> item {
                Text(
                    text = "Ошибка при загрузке: ${append.error.localizedMessage ?: "Неизвестная ошибка"}", //todo
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .padding(all = PaddingMedium)
                        .fillMaxWidth(),
                )
            }

            else -> Unit
        }
    }
}

// only for @Preview
@Preview
@Composable
fun PreviewHistoryListContent(
    @PreviewParameter(ListItemPreviewProvider::class, limit = 1)
    items: List<ListItem>
) {
    HistoryList(
        list = items,
        modifier = Modifier
    )
}
// only for @Preview
@Composable
private fun HistoryList(
    modifier: Modifier = Modifier,
    list: List<ListItem>,
) {
    LazyColumn(modifier = modifier) {
        items(list) { item ->
            when (item) {
                is ListItem.DateHeaderUiModel -> DateHeader(
                    modifier = modifier,
                    date = item.date,
                )
                is ListItem.ProductItemUiModel -> ProductItem(
                    product = item.product,
                    onEvent = {}
                )
            }
        }
    }
}
