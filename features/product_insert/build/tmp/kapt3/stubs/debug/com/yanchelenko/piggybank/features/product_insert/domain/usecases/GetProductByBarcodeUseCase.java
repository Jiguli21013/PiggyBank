package com.yanchelenko.piggybank.features.product_insert.domain.usecases;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0086B\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/yanchelenko/piggybank/features/product_insert/domain/usecases/GetProductByBarcodeUseCase;", "", "repository", "Lcom/yanchelenko/piggybank/domain/models/ProductsRepository;", "(Lcom/yanchelenko/piggybank/domain/models/ProductsRepository;)V", "invoke", "Lcom/yanchelneko/piggybank/common/core_utils/RequestResult;", "Lcom/yanchelenko/piggybank/domain/models/Product;", "barcode", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "product_insert_debug"})
public final class GetProductByBarcodeUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.yanchelenko.piggybank.domain.models.ProductsRepository repository = null;
    
    @javax.inject.Inject()
    public GetProductByBarcodeUseCase(@org.jetbrains.annotations.NotNull()
    com.yanchelenko.piggybank.domain.models.ProductsRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object invoke(@org.jetbrains.annotations.NotNull()
    java.lang.String barcode, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.yanchelneko.piggybank.common.core_utils.RequestResult<com.yanchelenko.piggybank.domain.models.Product>> $completion) {
        return null;
    }
}