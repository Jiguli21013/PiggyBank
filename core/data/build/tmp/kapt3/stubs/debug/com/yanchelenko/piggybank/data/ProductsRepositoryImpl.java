package com.yanchelenko.piggybank.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000  2\u00020\u0001:\u0001 B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000e0\rH\u0016J\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00100\rH\u0016J&\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00062\u0006\u0010\u0012\u001a\u00020\u0013H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0015J$\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\u0006\u0010\u0017\u001a\u00020\u0018H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0019\u0010\u001aJ$\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001c\u0010\u000bJ$\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00062\u0006\u0010\b\u001a\u00020\tH\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001f\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006!"}, d2 = {"Lcom/yanchelenko/piggybank/data/ProductsRepositoryImpl;", "Lcom/yanchelenko/piggybank/domain/models/ProductsRepository;", "productDao", "Lcom/yanchelenko/piggybank/core/database/dao/ProductDao;", "(Lcom/yanchelenko/piggybank/core/database/dao/ProductDao;)V", "deleteProductFromDatabase", "Lkotlin/Result;", "", "product", "Lcom/yanchelenko/piggybank/domain/models/Product;", "deleteProductFromDatabase-gIAlu-s", "(Lcom/yanchelenko/piggybank/domain/models/Product;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllProductsFromDatabase", "Lkotlinx/coroutines/flow/Flow;", "", "getPagedProducts", "Landroidx/paging/PagingData;", "getProductByBarcode", "barcode", "", "getProductByBarcode-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProductById", "productId", "", "getProductById-gIAlu-s", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveProductToDatabase", "saveProductToDatabase-gIAlu-s", "updateProductDatabase", "", "updateProductDatabase-gIAlu-s", "Companion", "data_debug"})
public final class ProductsRepositoryImpl implements com.yanchelenko.piggybank.domain.models.ProductsRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.yanchelenko.piggybank.core.database.dao.ProductDao productDao = null;
    @org.jetbrains.annotations.NotNull()
    @java.lang.Deprecated()
    public static final java.lang.String LOG_TAG = "ArticlesRepository";
    @org.jetbrains.annotations.NotNull()
    private static final com.yanchelenko.piggybank.data.ProductsRepositoryImpl.Companion Companion = null;
    
    @javax.inject.Inject()
    public ProductsRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.yanchelenko.piggybank.core.database.dao.ProductDao productDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.yanchelenko.piggybank.domain.models.Product>> getPagedProducts() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.yanchelenko.piggybank.domain.models.Product>> getAllProductsFromDatabase() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/yanchelenko/piggybank/data/ProductsRepositoryImpl$Companion;", "", "()V", "LOG_TAG", "", "data_debug"})
    static final class Companion {
        
        private Companion() {
            super();
        }
    }
}