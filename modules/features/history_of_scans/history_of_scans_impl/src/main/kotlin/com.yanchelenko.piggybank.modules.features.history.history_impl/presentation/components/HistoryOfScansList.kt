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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.yanchelenko.piggybank.modules.base.ui_kit.animations.AnimationDurations.LONG
import com.yanchelenko.piggybank.modules.base.ui_kit.animations.AnimationDurations.MEDIUM
import com.yanchelenko.piggybank.modules.base.ui_kit.components.DateHeader
import com.yanchelenko.piggybank.modules.base.ui_kit.test.UiTestTags
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.models.ListItem
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.preview.ListItemPreviewProvider
import com.yanchelenko.piggybank.modules.features.history.history_impl.presentation.state.HistoryOfScansEvent
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.PaddingMedium
import com.yanchelenko.piggybank.modules.dev_tools.TrackStateRecomposition

@Composable
internal fun HistoryOfScansList(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<ListItem>,
    onEvent: (HistoryOfScansEvent) -> Unit,
) {

    TrackStateRecomposition(
        composableName = "HistoryOfScansList",
        trackMap = mapOf(
            "itemCount" to items.itemCount,
            "refreshState" to items.loadState.refresh::class.simpleName,
            "appendState" to items.loadState.append::class.simpleName,
            "snapshotSize" to items.itemSnapshotList.items.size
        )
    )

    LazyColumn(
        modifier = modifier.semantics { contentDescription = UiTestTags.HISTORY_OF_SCANS_LIST }
    ) {
        items(
            count = items.itemCount,
            key = { index ->
                when (val it = items[index]) {
                    is ListItem.ScannedProductItemUiModel -> "p_${it.scannedProduct.productId}"
                    is ListItem.DateHeaderUiModel  -> "d_${it.date}"
                    else                           -> "ph_$index"
                }
            },
            contentType = { index ->
                when (items[index]) {
                    is ListItem.ScannedProductItemUiModel -> "product"
                    is ListItem.DateHeaderUiModel  -> "date"
                    else                           -> "placeholder"
                }
            }
        ) { index ->
            when (val item = items[index]) {
                is ListItem.DateHeaderUiModel -> DateHeader(
                    date = item.date,
                    modifier = Modifier
                        .animateItem(
                            placementSpec = tween(durationMillis = LONG)
                        ).semantics { contentDescription = UiTestTags.HISTORY_OF_SCANS_ITEM_PREFIX + item.date.toString() }
                )
                is ListItem.ScannedProductItemUiModel -> ScannedProductItem(
                    product = item.scannedProduct,
                    onEvent = onEvent,
                    modifier = Modifier
                        .animateItem(
                            fadeInSpec = tween(durationMillis = MEDIUM),
                            placementSpec = tween(durationMillis = LONG),
                            fadeOutSpec = tween(durationMillis = MEDIUM)
                        ).semantics { contentDescription = UiTestTags.HISTORY_OF_SCANS_ITEM_PREFIX + item.scannedProduct.productId.toString() }
                )
                null -> {} //todo shimmer ?
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
    HistoryOfScansListPreviewContent(
        list = items,
        modifier = Modifier
    )
}
// only for @Preview
@Composable
private fun HistoryOfScansListPreviewContent(
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
                is ListItem.ScannedProductItemUiModel -> ScannedProductItem(
                    product = item.scannedProduct,
                    onEvent = {}
                )
            }
        }
    }
}
