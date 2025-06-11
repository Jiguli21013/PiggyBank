package com.yanchelenko.piggybank.fearues.history.presentation.state;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0082\u0001\u0002\u0004\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/yanchelenko/piggybank/fearues/history/presentation/state/HistoryEffect;", "", "NavigateToDetails", "ShowError", "Lcom/yanchelenko/piggybank/fearues/history/presentation/state/HistoryEffect$NavigateToDetails;", "Lcom/yanchelenko/piggybank/fearues/history/presentation/state/HistoryEffect$ShowError;", "history_debug"})
public abstract interface HistoryEffect {
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/yanchelenko/piggybank/fearues/history/presentation/state/HistoryEffect$NavigateToDetails;", "Lcom/yanchelenko/piggybank/fearues/history/presentation/state/HistoryEffect;", "product", "Lcom/yanchelenko/piggybank/common/ui_models/ProductUiModel;", "(Lcom/yanchelenko/piggybank/common/ui_models/ProductUiModel;)V", "getProduct", "()Lcom/yanchelenko/piggybank/common/ui_models/ProductUiModel;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "history_debug"})
    public static final class NavigateToDetails implements com.yanchelenko.piggybank.fearues.history.presentation.state.HistoryEffect {
        @org.jetbrains.annotations.NotNull()
        private final com.yanchelenko.piggybank.common.ui_models.ProductUiModel product = null;
        
        public NavigateToDetails(@org.jetbrains.annotations.NotNull()
        com.yanchelenko.piggybank.common.ui_models.ProductUiModel product) {
            super();
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
        public final com.yanchelenko.piggybank.fearues.history.presentation.state.HistoryEffect.NavigateToDetails copy(@org.jetbrains.annotations.NotNull()
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/yanchelenko/piggybank/fearues/history/presentation/state/HistoryEffect$ShowError;", "Lcom/yanchelenko/piggybank/fearues/history/presentation/state/HistoryEffect;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "history_debug"})
    public static final class ShowError implements com.yanchelenko.piggybank.fearues.history.presentation.state.HistoryEffect {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String message = null;
        
        public ShowError(@org.jetbrains.annotations.NotNull()
        java.lang.String message) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getMessage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.yanchelenko.piggybank.fearues.history.presentation.state.HistoryEffect.ShowError copy(@org.jetbrains.annotations.NotNull()
        java.lang.String message) {
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