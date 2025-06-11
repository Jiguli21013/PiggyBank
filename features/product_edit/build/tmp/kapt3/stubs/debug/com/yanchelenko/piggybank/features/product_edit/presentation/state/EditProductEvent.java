package com.yanchelenko.piggybank.features.product_edit.presentation.state;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0007\u0003\u0004\u0005\u0006\u0007\b\tB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0007\n\u000b\f\r\u000e\u000f\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEvent;", "", "()V", "GoBackToScanner", "LoadProductByProductId", "PriceChanged", "ProductFoundInDB", "ProductNameChanged", "SaveProduct", "WeightChanged", "Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEvent$GoBackToScanner;", "Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEvent$LoadProductByProductId;", "Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEvent$PriceChanged;", "Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEvent$ProductFoundInDB;", "Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEvent$ProductNameChanged;", "Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEvent$SaveProduct;", "Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEvent$WeightChanged;", "product_edit_debug"})
public abstract class EditProductEvent {
    
    private EditProductEvent() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c7\n\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00d6\u0003J\t\u0010\u0007\u001a\u00020\bH\u00d6\u0001J\t\u0010\t\u001a\u00020\nH\u00d6\u0001\u00a8\u0006\u000b"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEvent$GoBackToScanner;", "Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEvent;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "product_edit_debug"})
    public static final class GoBackToScanner extends com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductEvent {
        @org.jetbrains.annotations.NotNull()
        public static final com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductEvent.GoBackToScanner INSTANCE = null;
        
        private GoBackToScanner() {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEvent$LoadProductByProductId;", "Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEvent;", "productId", "", "(J)V", "getProductId", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "product_edit_debug"})
    public static final class LoadProductByProductId extends com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductEvent {
        private final long productId = 0L;
        
        public LoadProductByProductId(long productId) {
        }
        
        public final long getProductId() {
            return 0L;
        }
        
        public final long component1() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductEvent.LoadProductByProductId copy(long productId) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEvent$PriceChanged;", "Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEvent;", "price", "", "(D)V", "getPrice", "()D", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "product_edit_debug"})
    public static final class PriceChanged extends com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductEvent {
        private final double price = 0.0;
        
        public PriceChanged(double price) {
        }
        
        public final double getPrice() {
            return 0.0;
        }
        
        public final double component1() {
            return 0.0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductEvent.PriceChanged copy(double price) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEvent$ProductFoundInDB;", "Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEvent;", "product", "Lcom/yanchelenko/piggybank/common/ui_models/ProductUiModel;", "(Lcom/yanchelenko/piggybank/common/ui_models/ProductUiModel;)V", "getProduct", "()Lcom/yanchelenko/piggybank/common/ui_models/ProductUiModel;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "product_edit_debug"})
    public static final class ProductFoundInDB extends com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductEvent {
        @org.jetbrains.annotations.NotNull()
        private final com.yanchelenko.piggybank.common.ui_models.ProductUiModel product = null;
        
        public ProductFoundInDB(@org.jetbrains.annotations.NotNull()
        com.yanchelenko.piggybank.common.ui_models.ProductUiModel product) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.yanchelenko.piggybank.common.ui_models.ProductUiModel getProduct() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.yanchelenko.piggybank.common.ui_models.ProductUiModel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductEvent.ProductFoundInDB copy(@org.jetbrains.annotations.NotNull()
        com.yanchelenko.piggybank.common.ui_models.ProductUiModel product) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEvent$ProductNameChanged;", "Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEvent;", "name", "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "product_edit_debug"})
    public static final class ProductNameChanged extends com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductEvent {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String name = null;
        
        public ProductNameChanged(@org.jetbrains.annotations.NotNull()
        java.lang.String name) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductEvent.ProductNameChanged copy(@org.jetbrains.annotations.NotNull()
        java.lang.String name) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c7\n\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00d6\u0003J\t\u0010\u0007\u001a\u00020\bH\u00d6\u0001J\t\u0010\t\u001a\u00020\nH\u00d6\u0001\u00a8\u0006\u000b"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEvent$SaveProduct;", "Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEvent;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "product_edit_debug"})
    public static final class SaveProduct extends com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductEvent {
        @org.jetbrains.annotations.NotNull()
        public static final com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductEvent.SaveProduct INSTANCE = null;
        
        private SaveProduct() {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEvent$WeightChanged;", "Lcom/yanchelenko/piggybank/features/product_edit/presentation/state/EditProductEvent;", "weight", "", "(D)V", "getWeight", "()D", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "product_edit_debug"})
    public static final class WeightChanged extends com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductEvent {
        private final double weight = 0.0;
        
        public WeightChanged(double weight) {
        }
        
        public final double getWeight() {
            return 0.0;
        }
        
        public final double component1() {
            return 0.0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.yanchelenko.piggybank.features.product_edit.presentation.state.EditProductEvent.WeightChanged copy(double weight) {
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
}