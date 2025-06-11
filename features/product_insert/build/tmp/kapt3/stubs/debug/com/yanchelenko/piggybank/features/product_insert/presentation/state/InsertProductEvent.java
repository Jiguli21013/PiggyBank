package com.yanchelenko.piggybank.features.product_insert.presentation.state;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\b\u0003\u0004\u0005\u0006\u0007\b\t\nB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\b\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u00a8\u0006\u0013"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent;", "", "()V", "CurrencyChanged", "GoBackToScanner", "LoadProductByBarcode", "PriceChanged", "ProductFoundInDB", "ProductNameChanged", "SaveProduct", "WeightChanged", "Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent$CurrencyChanged;", "Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent$GoBackToScanner;", "Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent$LoadProductByBarcode;", "Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent$PriceChanged;", "Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent$ProductFoundInDB;", "Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent$ProductNameChanged;", "Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent$SaveProduct;", "Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent$WeightChanged;", "product_insert_debug"})
public abstract class InsertProductEvent {
    
    private InsertProductEvent() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent$CurrencyChanged;", "Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent;", "currency", "", "(Ljava/lang/String;)V", "getCurrency", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "product_insert_debug"})
    public static final class CurrencyChanged extends com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEvent {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String currency = null;
        
        public CurrencyChanged(@org.jetbrains.annotations.NotNull()
        java.lang.String currency) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getCurrency() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEvent.CurrencyChanged copy(@org.jetbrains.annotations.NotNull()
        java.lang.String currency) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c7\n\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00d6\u0003J\t\u0010\u0007\u001a\u00020\bH\u00d6\u0001J\t\u0010\t\u001a\u00020\nH\u00d6\u0001\u00a8\u0006\u000b"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent$GoBackToScanner;", "Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "product_insert_debug"})
    public static final class GoBackToScanner extends com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEvent {
        @org.jetbrains.annotations.NotNull()
        public static final com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEvent.GoBackToScanner INSTANCE = null;
        
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent$LoadProductByBarcode;", "Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent;", "barcode", "", "(Ljava/lang/String;)V", "getBarcode", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "product_insert_debug"})
    public static final class LoadProductByBarcode extends com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEvent {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String barcode = null;
        
        public LoadProductByBarcode(@org.jetbrains.annotations.NotNull()
        java.lang.String barcode) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getBarcode() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEvent.LoadProductByBarcode copy(@org.jetbrains.annotations.NotNull()
        java.lang.String barcode) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent$PriceChanged;", "Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent;", "price", "", "(D)V", "getPrice", "()D", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "product_insert_debug"})
    public static final class PriceChanged extends com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEvent {
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
        public final com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEvent.PriceChanged copy(double price) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent$ProductFoundInDB;", "Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent;", "product", "Lcom/yanchelenko/piggybank/common/ui_models/ProductUiModel;", "(Lcom/yanchelenko/piggybank/common/ui_models/ProductUiModel;)V", "getProduct", "()Lcom/yanchelenko/piggybank/common/ui_models/ProductUiModel;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "product_insert_debug"})
    public static final class ProductFoundInDB extends com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEvent {
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
        public final com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEvent.ProductFoundInDB copy(@org.jetbrains.annotations.NotNull()
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent$ProductNameChanged;", "Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent;", "name", "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "product_insert_debug"})
    public static final class ProductNameChanged extends com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEvent {
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
        public final com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEvent.ProductNameChanged copy(@org.jetbrains.annotations.NotNull()
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c7\n\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00d6\u0003J\t\u0010\u0007\u001a\u00020\bH\u00d6\u0001J\t\u0010\t\u001a\u00020\nH\u00d6\u0001\u00a8\u0006\u000b"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent$SaveProduct;", "Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "product_insert_debug"})
    public static final class SaveProduct extends com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEvent {
        @org.jetbrains.annotations.NotNull()
        public static final com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEvent.SaveProduct INSTANCE = null;
        
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent$WeightChanged;", "Lcom/yanchelenko/piggybank/features/product_insert/presentation/state/InsertProductEvent;", "weight", "", "(D)V", "getWeight", "()D", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "product_insert_debug"})
    public static final class WeightChanged extends com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEvent {
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
        public final com.yanchelenko.piggybank.features.product_insert.presentation.state.InsertProductEvent.WeightChanged copy(double weight) {
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