package com.yanchelenko.piggybank.fearues.history.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
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
import com.yanchelenko.piggybank.core.debugUI.debug.WithDebug
import com.yanchelenko.piggybank.fearues.history.presentation.models.ListItem
import com.yanchelenko.piggybank.fearues.history.presentation.preview.ListItemPreviewProvider
import com.yanchelenko.piggybank.fearues.history.presentation.state.HistoryEvent
import com.yanchelenko.piggynank.core.ui.theme.Dimens.PaddingMedium

@Composable
internal fun HistoryList(
    items: LazyPagingItems<ListItem>,
    modifier: Modifier = Modifier,
    onEvent: (HistoryEvent) -> Unit
) {
    WithDebug(
        trackMap = mapOf(
            "itemCount" to items.itemCount,
            "loadState.refresh" to items.loadState.refresh::class.simpleName,
            "loadState.append" to items.loadState.append::class.simpleName,
            "snapshotHash" to items.itemSnapshotList.items.hashCode()
        ),
        composableName = "HistoryList"
    ) {
        LazyColumn(modifier = modifier) {
            items(
                count = items.itemCount,
                key = { index ->
                    when (val item = items[index]) {
                        is ListItem.DateHeader -> "header_${item.date}"
                        is ListItem.ProductItem -> "product_${item.product.productId}"
                        else -> "placeholder_$index"
                    }
                }
            ) { index ->
                when (val item = items[index]) {
                    is ListItem.DateHeader -> DateHeader(date = item.date)
                    is ListItem.ProductItem -> ProductItem(
                        product = item.product,
                        onEvent = onEvent
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
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }

                is LoadState.Error -> item {
                    Text(
                        text = "Ошибка при загрузке: ${append.error.localizedMessage ?: "Неизвестная ошибка"}", //todo
                        modifier = Modifier
                            .padding(all = PaddingMedium)
                            .fillMaxWidth(),
                        color = MaterialTheme.colorScheme.error
                    )
                }

                else -> Unit
            }
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
        modifier = Modifier.fillMaxSize()
    )
}
// only for @Preview
@Composable
private fun HistoryList(
    list: List<ListItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(list) { item ->
            when (item) {
                is ListItem.DateHeader -> DateHeader(date = item.date)
                is ListItem.ProductItem -> ProductItem(
                    product = item.product,
                    onEvent = {}
                )
            }
        }
    }
}
