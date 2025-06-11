package com.yanchelenko.piggybank.features.product_details.presentation.state;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u0082\u0001\u0004\u0006\u0007\b\t\u00a8\u0006\n"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEvent;", "", "CancelDelete", "ConfirmedDelete", "OnDeleteClicked", "OnEditClicked", "Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEvent$CancelDelete;", "Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEvent$ConfirmedDelete;", "Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEvent$OnDeleteClicked;", "Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEvent$OnEditClicked;", "product_details_debug"})
public abstract interface ProductDetailsEvent {
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEvent$CancelDelete;", "Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEvent;", "()V", "product_details_debug"})
    public static final class CancelDelete implements com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEvent {
        @org.jetbrains.annotations.NotNull()
        public static final com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEvent.CancelDelete INSTANCE = null;
        
        private CancelDelete() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEvent$ConfirmedDelete;", "Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEvent;", "()V", "product_details_debug"})
    public static final class ConfirmedDelete implements com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEvent {
        @org.jetbrains.annotations.NotNull()
        public static final com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEvent.ConfirmedDelete INSTANCE = null;
        
        private ConfirmedDelete() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEvent$OnDeleteClicked;", "Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEvent;", "()V", "product_details_debug"})
    public static final class OnDeleteClicked implements com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEvent {
        @org.jetbrains.annotations.NotNull()
        public static final com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEvent.OnDeleteClicked INSTANCE = null;
        
        private OnDeleteClicked() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEvent$OnEditClicked;", "Lcom/yanchelenko/piggybank/features/product_details/presentation/state/ProductDetailsEvent;", "()V", "product_details_debug"})
    public static final class OnEditClicked implements com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEvent {
        @org.jetbrains.annotations.NotNull()
        public static final com.yanchelenko.piggybank.features.product_details.presentation.state.ProductDetailsEvent.OnEditClicked INSTANCE = null;
        
        private OnEditClicked() {
            super();
        }
    }
}