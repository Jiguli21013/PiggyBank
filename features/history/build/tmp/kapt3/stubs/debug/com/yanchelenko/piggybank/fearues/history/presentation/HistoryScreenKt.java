package com.yanchelenko.piggybank.fearues.history.presentation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a<\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\nH\u0007\u001a&\u0010\f\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\nH\u0007\u001a.\u0010\f\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\nH\u0001\u00a8\u0006\u0011"}, d2 = {"HistoryMainContent", "", "modifier", "Landroidx/compose/ui/Modifier;", "currentHistoryState", "Lcom/yanchelenko/piggybank/fearues/history/presentation/state/HistoryState;", "items", "Landroidx/paging/compose/LazyPagingItems;", "Lcom/yanchelenko/piggybank/fearues/history/presentation/components/ListItem;", "onEvent", "Lkotlin/Function1;", "Lcom/yanchelenko/piggybank/fearues/history/presentation/state/HistoryEvent;", "HistoryMainScreen", "onNavigateToProductDetails", "Lcom/yanchelenko/piggybank/common/ui_models/ProductUiModel;", "viewModel", "Lcom/yanchelenko/piggybank/fearues/history/presentation/HistoryViewModel;", "history_debug"})
public final class HistoryScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void HistoryMainScreen(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.yanchelenko.piggybank.common.ui_models.ProductUiModel, kotlin.Unit> onNavigateToProductDetails) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void HistoryMainScreen(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    com.yanchelenko.piggybank.fearues.history.presentation.HistoryViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.yanchelenko.piggybank.common.ui_models.ProductUiModel, kotlin.Unit> onNavigateToProductDetails) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void HistoryMainContent(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    com.yanchelenko.piggybank.fearues.history.presentation.state.HistoryState currentHistoryState, @org.jetbrains.annotations.NotNull()
    androidx.paging.compose.LazyPagingItems<com.yanchelenko.piggybank.fearues.history.presentation.components.ListItem> items, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.yanchelenko.piggybank.fearues.history.presentation.state.HistoryEvent, kotlin.Unit> onEvent) {
    }
}