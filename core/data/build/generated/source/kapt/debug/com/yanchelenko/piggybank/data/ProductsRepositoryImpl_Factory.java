package com.yanchelenko.piggybank.data;

import com.yanchelenko.piggybank.core.database.dao.ProductDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class ProductsRepositoryImpl_Factory implements Factory<ProductsRepositoryImpl> {
  private final Provider<ProductDao> productDaoProvider;

  public ProductsRepositoryImpl_Factory(Provider<ProductDao> productDaoProvider) {
    this.productDaoProvider = productDaoProvider;
  }

  @Override
  public ProductsRepositoryImpl get() {
    return newInstance(productDaoProvider.get());
  }

  public static ProductsRepositoryImpl_Factory create(Provider<ProductDao> productDaoProvider) {
    return new ProductsRepositoryImpl_Factory(productDaoProvider);
  }

  public static ProductsRepositoryImpl newInstance(ProductDao productDao) {
    return new ProductsRepositoryImpl(productDao);
  }
}
