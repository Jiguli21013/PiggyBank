package com.yanchelenko.piggybank.fearues.history.presentation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0002H\u0016J\u0016\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001b"}, d2 = {"Lcom/yanchelenko/piggybank/fearues/history/presentation/HistoryViewModel;", "Lcom/yanchelenko/piggynank/core/ui/base/BaseViewModel;", "Lcom/yanchelenko/piggybank/fearues/history/presentation/state/HistoryEvent;", "Lcom/yanchelenko/piggybank/fearues/history/presentation/state/HistoryState;", "Lcom/yanchelenko/piggybank/fearues/history/presentation/state/HistoryEffect;", "getPagedProductsUseCase", "Lcom/yanchelenko/piggybank/fearues/history/domain/GetPagedProductsUseCase;", "deleteProductUseCase", "Lcom/yanchelenko/piggybank/domain/usecases/DeleteProductUseCase;", "(Lcom/yanchelenko/piggybank/fearues/history/domain/GetPagedProductsUseCase;Lcom/yanchelenko/piggybank/domain/usecases/DeleteProductUseCase;)V", "items", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PagingData;", "Lcom/yanchelenko/piggybank/fearues/history/presentation/components/ListItem;", "getItems", "()Lkotlinx/coroutines/flow/Flow;", "deleteProduct", "", "product", "Lcom/yanchelenko/piggybank/common/ui_models/ProductUiModel;", "onEvent", "event", "onLoadStateChanged", "loadState", "Landroidx/paging/CombinedLoadStates;", "itemCount", "", "history_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class HistoryViewModel extends com.yanchelenko.piggynank.core.ui.base.BaseViewModel<com.yanchelenko.piggybank.fearues.history.presentation.state.HistoryEvent, com.yanchelenko.piggybank.fearues.history.presentation.state.HistoryState, com.yanchelenko.piggybank.fearues.history.presentation.state.HistoryEffect> {
    @org.jetbrains.annotations.NotNull()
    private final com.yanchelenko.piggybank.fearues.history.domain.GetPagedProductsUseCase getPagedProductsUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.yanchelenko.piggybank.domain.usecases.DeleteProductUseCase deleteProductUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.yanchelenko.piggybank.fearues.history.presentation.components.ListItem>> items = null;
    
    @javax.inject.Inject()
    public HistoryViewModel(@org.jetbrains.annotations.NotNull()
    com.yanchelenko.piggybank.fearues.history.domain.GetPagedProductsUseCase getPagedProductsUseCase, @org.jetbrains.annotations.NotNull()
    com.yanchelenko.piggybank.domain.usecases.DeleteProductUseCase deleteProductUseCase) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.yanchelenko.piggybank.fearues.history.presentation.components.ListItem>> getItems() {
        return null;
    }
    
    public final void onLoadStateChanged(@org.jetbrains.annotations.NotNull()
    androidx.paging.CombinedLoadStates loadState, int itemCount) {
    }
    
    @java.lang.Override()
    public void onEvent(@org.jetbrains.annotations.NotNull()
    com.yanchelenko.piggybank.fearues.history.presentation.state.HistoryEvent event) {
    }
    
    private final void deleteProduct(com.yanchelenko.piggybank.common.ui_models.ProductUiModel product) {
    }
}