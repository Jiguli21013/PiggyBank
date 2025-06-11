package com.yanchelenko.piggybank.features.product_insert.presentation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001B\'\b\u0007\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0002H\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_insert/presentation/InsertProductScreenViewModel;", "Lcom/yanchelenko/piggynank/core/ui/base/BaseViewModel;", "Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent;", "Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductUiState;", "Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEffect;", "insertNewProductUseCase", "Lcom/yanchelenko/piggybank/features/product_insert/domain/usecases/InsertNewProductUseCase;", "getProductByBarcodeUseCase", "Lcom/yanchelenko/piggybank/features/product_insert/domain/usecases/GetProductByBarcodeUseCase;", "getPricePerKgUseCase", "Lcom/yanchelenko/piggybank/domain/usecases/GetPricePerKgUseCase;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "(Lcom/yanchelenko/piggybank/features/product_insert/domain/usecases/InsertNewProductUseCase;Lcom/yanchelenko/piggybank/features/product_insert/domain/usecases/GetProductByBarcodeUseCase;Lcom/yanchelenko/piggybank/domain/usecases/GetPricePerKgUseCase;Landroidx/lifecycle/SavedStateHandle;)V", "insertProductToDB", "", "loadProductByBarcode", "barcode", "", "onEvent", "event", "product_insert_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class InsertProductScreenViewModel extends com.yanchelenko.piggynank.core.ui.base.BaseViewModel<com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEvent, com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductUiState, com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEffect> {
    @org.jetbrains.annotations.NotNull()
    private final com.yanchelenko.piggybank.features.product_insert.domain.usecases.InsertNewProductUseCase insertNewProductUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.yanchelenko.piggybank.features.product_insert.domain.usecases.GetProductByBarcodeUseCase getProductByBarcodeUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.yanchelenko.piggybank.domain.usecases.GetPricePerKgUseCase getPricePerKgUseCase = null;
    
    @javax.inject.Inject()
    public InsertProductScreenViewModel(@org.jetbrains.annotations.NotNull()
    com.yanchelenko.piggybank.features.product_insert.domain.usecases.InsertNewProductUseCase insertNewProductUseCase, @org.jetbrains.annotations.NotNull()
    com.yanchelenko.piggybank.features.product_insert.domain.usecases.GetProductByBarcodeUseCase getProductByBarcodeUseCase, @org.jetbrains.annotations.NotNull()
    com.yanchelenko.piggybank.domain.usecases.GetPricePerKgUseCase getPricePerKgUseCase, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle) {
        super(null);
    }
    
    @java.lang.Override()
    public void onEvent(@org.jetbrains.annotations.NotNull()
    com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEvent event) {
    }
    
    private final void loadProductByBarcode(java.lang.String barcode) {
    }
    
    private final void insertProductToDB() {
    }
}