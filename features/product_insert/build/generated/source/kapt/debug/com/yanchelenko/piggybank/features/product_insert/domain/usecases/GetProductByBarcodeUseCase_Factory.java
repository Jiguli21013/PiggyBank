package com.yanchelenko.piggybank.features.product_insert.domain.usecases;

import com.yanchelenko.piggybank.domain.models.ProductsRepository;
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
public final class GetProductByBarcodeUseCase_Factory implements Factory<GetProductByBarcodeUseCase> {
  private final Provider<ProductsRepository> repositoryProvider;

  public GetProductByBarcodeUseCase_Factory(Provider<ProductsRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetProductByBarcodeUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetProductByBarcodeUseCase_Factory create(
      Provider<ProductsRepository> repositoryProvider) {
    return new GetProductByBarcodeUseCase_Factory(repositoryProvider);
  }

  public static GetProductByBarcodeUseCase newInstance(ProductsRepository repository) {
    return new GetProductByBarcodeUseCase(repository);
  }
}
