package com.yanchelenko.piggybank.features.product_edit.presentation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\b\u0007\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001B\'\b\u0007\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0002H\u0016J\b\u0010\u0014\u001a\u00020\u000fH\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_edit/presentation/EditProductViewModel;", "Lcom/yanchelenko/piggynank/core/ui/base/BaseViewModel;", "Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEvent;", "Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductUiState;", "Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEffect;", "updateProductUseCase", "Lcom/yanchelenko/piggybank/features/product_edit/domain/usecases/UpdateProductUseCase;", "getProductByProductIdUseCase", "Lcom/yanchelenko/piggybank/domain/usecases/GetProductByIdUseCase;", "getPricePerKgUseCase", "Lcom/yanchelenko/piggybank/domain/usecases/GetPricePerKgUseCase;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "(Lcom/yanchelenko/piggybank/features/product_edit/domain/usecases/UpdateProductUseCase;Lcom/yanchelenko/piggybank/domain/usecases/GetProductByIdUseCase;Lcom/yanchelenko/piggybank/domain/usecases/GetPricePerKgUseCase;Landroidx/lifecycle/SavedStateHandle;)V", "loadProductByProductId", "", "productId", "", "onEvent", "event", "updateProductDB", "product_edit_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class EditProductViewModel extends com.yanchelenko.piggynank.core.ui.base.BaseViewModel<com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductEvent, com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductUiState, com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductEffect> {
    @org.jetbrains.annotations.NotNull()
    private final com.yanchelenko.piggybank.features.product_edit.domain.usecases.UpdateProductUseCase updateProductUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.yanchelenko.piggybank.domain.usecases.GetProductByIdUseCase getProductByProductIdUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.yanchelenko.piggybank.domain.usecases.GetPricePerKgUseCase getPricePerKgUseCase = null;
    
    @javax.inject.Inject()
    public EditProductViewModel(@org.jetbrains.annotations.NotNull()
    com.yanchelenko.piggybank.features.product_edit.domain.usecases.UpdateProductUseCase updateProductUseCase, @org.jetbrains.annotations.NotNull()
    com.yanchelenko.piggybank.domain.usecases.GetProductByIdUseCase getProductByProductIdUseCase, @org.jetbrains.annotations.NotNull()
    com.yanchelenko.piggybank.domain.usecases.GetPricePerKgUseCase getPricePerKgUseCase, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle) {
        super(null);
    }
    
    @java.lang.Override()
    public void onEvent(@org.jetbrains.annotations.NotNull()
    com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductEvent event) {
    }
    
    private final void loadProductByProductId(long productId) {
    }
    
    private final void updateProductDB() {
    }
}