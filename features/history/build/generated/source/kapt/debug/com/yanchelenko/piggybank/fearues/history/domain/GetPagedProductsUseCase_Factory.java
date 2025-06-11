package com.yanchelenko.piggybank.fearues.history.domain;

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
public final class GetPagedProductsUseCase_Factory implements Factory<GetPagedProductsUseCase> {
  private final Provider<ProductsRepository> repositoryProvider;

  public GetPagedProductsUseCase_Factory(Provider<ProductsRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetPagedProductsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetPagedProductsUseCase_Factory create(
      Provider<ProductsRepository> repositoryProvider) {
    return new GetPagedProductsUseCase_Factory(repositoryProvider);
  }

  public static GetPagedProductsUseCase newInstance(ProductsRepository repository) {
    return new GetPagedProductsUseCase(repository);
  }
}
