package com.yanchelenko.piggybank.features.product_edit.domain.usecases;

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
public final class UpdateProductUseCase_Factory implements Factory<UpdateProductUseCase> {
  private final Provider<ProductsRepository> repositoryProvider;

  public UpdateProductUseCase_Factory(Provider<ProductsRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public UpdateProductUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static UpdateProductUseCase_Factory create(
      Provider<ProductsRepository> repositoryProvider) {
    return new UpdateProductUseCase_Factory(repositoryProvider);
  }

  public static UpdateProductUseCase newInstance(ProductsRepository repository) {
    return new UpdateProductUseCase(repository);
  }
}
