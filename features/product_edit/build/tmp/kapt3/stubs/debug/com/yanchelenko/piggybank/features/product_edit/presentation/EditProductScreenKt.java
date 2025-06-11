package com.yanchelenko.piggybank.features.product_edit.presentation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a.\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u001a \u0010\t\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bH\u0007\u001a(\u0010\t\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bH\u0001\u001a\u0012\u0010\u000e\u001a\u00020\u00012\b\b\u0001\u0010\u0002\u001a\u00020\u0003H\u0003\u00a8\u0006\u000f"}, d2 = {"EditProductContent", "", "state", "Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductUiState;", "modifier", "Landroidx/compose/ui/Modifier;", "onEvent", "Lkotlin/Function1;", "Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEvent;", "EditProductScreen", "onNavigateBack", "Lkotlin/Function0;", "viewModel", "Lcom/yanchelenko/piggybank/features/product_edit/presentation/EditProductViewModel;", "InsertProductMainContentPreview", "product_edit_debug"})
public final class EditProductScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void EditProductScreen(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void EditProductScreen(@org.jetbrains.annotations.NotNull()
    com.yanchelenko.piggybank.features.product_edit.presentation.EditProductViewModel viewModel, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void EditProductContent(@org.jetbrains.annotations.NotNull()
    com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductUiState state, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductEvent, kotlin.Unit> onEvent) {
    }
    
    @androidx.compose.ui.tooling.preview.Preview(showBackground = true)
    @androidx.compose.runtime.Composable()
    private static final void InsertProductMainContentPreview(@androidx.compose.ui.tooling.preview.PreviewParameter(provider = com.yanchelenko.piggybank.features.product_edit.presentation.preview.EditProductPreviewProvider.class)
    com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductUiState state) {
    }
}