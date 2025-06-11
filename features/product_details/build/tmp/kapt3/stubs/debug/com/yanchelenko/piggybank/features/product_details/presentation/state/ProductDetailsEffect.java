package com.yanchelenko.piggybank.features.product_details.presentation.state;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u0082\u0001\u0004\u0006\u0007\b\t\u00a8\u0006\n"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEffect;", "", "CloseDeleteDialog", "NavigateBack", "NavigateToEdit", "ShowDeleteDialog", "Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEffect$CloseDeleteDialog;", "Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEffect$NavigateBack;", "Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEffect$NavigateToEdit;", "Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEffect$ShowDeleteDialog;", "product_details_debug"})
public abstract interface ProductDetailsEffect {
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEffect$CloseDeleteDialog;", "Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEffect;", "()V", "product_details_debug"})
    public static final class CloseDeleteDialog implements com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEffect {
        @org.jetbrains.annotations.NotNull()
        public static final com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEffect.CloseDeleteDialog INSTANCE = null;
        
        private CloseDeleteDialog() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEffect$NavigateBack;", "Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEffect;", "()V", "product_details_debug"})
    public static final class NavigateBack implements com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEffect {
        @org.jetbrains.annotations.NotNull()
        public static final com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEffect.NavigateBack INSTANCE = null;
        
        private NavigateBack() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEffect$NavigateToEdit;", "Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEffect;", "productId", "", "(J)V", "getProductId", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "product_details_debug"})
    public static final class NavigateToEdit implements com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEffect {
        private final long productId = 0L;
        
        public NavigateToEdit(long productId) {
            super();
        }
        
        public final long getProductId() {
            return 0L;
        }
        
        public final long component1() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEffect.NavigateToEdit copy(long productId) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEffect$ShowDeleteDialog;", "Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEffect;", "()V", "product_details_debug"})
    public static final class ShowDeleteDialog implements com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEffect {
        @org.jetbrains.annotations.NotNull()
        public static final com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEffect.ShowDeleteDialog INSTANCE = null;
        
        private ShowDeleteDialog() {
            super();
        }
    }
}