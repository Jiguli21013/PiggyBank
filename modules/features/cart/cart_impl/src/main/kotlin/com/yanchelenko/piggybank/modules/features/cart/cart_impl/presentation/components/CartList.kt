package com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.components

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
import com.yanchelenko.piggybank.modules.dev_tools.RebuggerIfDebug
import com.yanchelenko.piggybank.modules.base.ui_kit.animations.AnimationDurations.LONG
import com.yanchelenko.piggybank.modules.base.ui_kit.animations.AnimationDurations.MEDIUM
import com.yanchelenko.piggybank.modules.base.ui_model.models.ProductOfCartUiModel
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.preview.ListProductsOfCartPreviewProvider
import com.yanchelenko.piggybank.modules.features.cart.cart_impl.presentation.state.CartEvent
import com.yanchelenko.piggybank.modules.base.ui_kit.theme.Dimens.PaddingMedium

@Composable
internal fun CartList(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<ProductOfCartUiModel>,
    onEvent: (CartEvent) -> Unit,
) {

    RebuggerIfDebug(
        composableName = "CartList",
        trackMap = mapOf(
            "itemCount" to items.itemCount,
            "loadState.refresh" to items.loadState.refresh::class.simpleName,
            "loadState.append" to items.loadState.append::class.simpleName,
            "snapshotHash" to items.itemSnapshotList.items.hashCode()
        )
    )

    LazyColumn(
        modifier = modifier.semantics { contentDescription = "cart_list" }
    ) {
        items(
            count = items.itemCount,
            key = { index ->
                val it = items.peek(index)
                it?.cartItemId ?: "placeholder_$index"
            },
            contentType = { "product_of_cart" }
        ) { index ->
            val item = items[index]
            if (item != null) {
                ProductOfCartItem(
                    productOfCart = item,
                    onEvent = onEvent,
                    modifier = Modifier
                        .animateItem(
                            fadeInSpec = tween(durationMillis = MEDIUM),
                            placementSpec = tween(durationMillis = LONG),
                            fadeOutSpec = tween(durationMillis = MEDIUM)
                        )
                        .semantics { contentDescription = "cart_item_${item.cartItemId}" }
                )
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
    @PreviewParameter(ListProductsOfCartPreviewProvider::class, limit = 1)
    items: List<ProductOfCartUiModel>
) {
    CartList(
        list = items,
        modifier = Modifier
    )
}
// only for @Preview
@Composable
private fun CartList(
    modifier: Modifier = Modifier,
    list: List<ProductOfCartUiModel>,
) {
    LazyColumn(modifier = modifier) {
        items(list) { item ->

            ProductOfCartItem(
                productOfCart = item,
                onEvent = {}
            )
        }
    }
}
