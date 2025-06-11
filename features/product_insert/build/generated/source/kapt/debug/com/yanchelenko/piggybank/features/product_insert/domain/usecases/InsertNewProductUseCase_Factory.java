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
public final class InsertNewProductUseCase_Factory implements Factory<InsertNewProductUseCase> {
  private final Provider<ProductsRepository> repositoryProvider;

  public InsertNewProductUseCase_Factory(Provider<ProductsRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public InsertNewProductUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static InsertNewProductUseCase_Factory create(
      Provider<ProductsRepository> repositoryProvider) {
    return new InsertNewProductUseCase_Factory(repositoryProvider);
  }

  public static InsertNewProductUseCase newInstance(ProductsRepository repository) {
    return new InsertNewProductUseCase(repository);
  }
}
