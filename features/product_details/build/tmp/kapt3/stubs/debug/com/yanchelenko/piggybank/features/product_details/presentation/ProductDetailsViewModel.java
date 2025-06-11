package com.yanchelenko.piggybank.features.product_details.presentation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\b\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_details/presentation/ProductDetailsViewModel;", "Lcom/yanchelenko/piggynank/core/ui/base/BaseViewModel;", "Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEvent;", "Lcom/yanchelenko/piggybank/common/ui_models/ProductUiModel;", "Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEffect;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "deleteProductUseCase", "Lcom/yanchelenko/piggybank/domain/usecases/DeleteProductUseCase;", "(Landroidx/lifecycle/SavedStateHandle;Lcom/yanchelenko/piggybank/domain/usecases/DeleteProductUseCase;)V", "deleteProductFromDB", "", "onEvent", "event", "product_details_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ProductDetailsViewModel extends com.yanchelenko.piggynank.core.ui.base.BaseViewModel<com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEvent, com.yanchelenko.piggybank.common.ui_models.ProductUiModel, com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEffect> {
    @org.jetbrains.annotations.NotNull()
    private final com.yanchelenko.piggybank.domain.usecases.DeleteProductUseCase deleteProductUseCase = null;
    
    @javax.inject.Inject()
    public ProductDetailsViewModel(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle, @org.jetbrains.annotations.NotNull()
    com.yanchelenko.piggybank.domain.usecases.DeleteProductUseCase deleteProductUseCase) {
        super(null);
    }
    
    @java.lang.Override()
    public void onEvent(@org.jetbrains.annotations.NotNull()
    com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEvent event) {
    }
    
    private final void deleteProductFromDB() {
    }
}