package com.yanchelenko.piggybank.features.product_details.presentation;

import androidx.lifecycle.SavedStateHandle;
import com.yanchelenko.piggybank.domain.usecases.DeleteProductUseCase;
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
public final class ProductDetailsViewModel_Factory implements Factory<ProductDetailsViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<DeleteProductUseCase> deleteProductUseCaseProvider;

  public ProductDetailsViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<DeleteProductUseCase> deleteProductUseCaseProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.deleteProductUseCaseProvider = deleteProductUseCaseProvider;
  }

  @Override
  public ProductDetailsViewModel get() {
    return newInstance(savedStateHandleProvider.get(), deleteProductUseCaseProvider.get());
  }

  public static ProductDetailsViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<DeleteProductUseCase> deleteProductUseCaseProvider) {
    return new ProductDetailsViewModel_Factory(savedStateHandleProvider, deleteProductUseCaseProvider);
  }

  public static ProductDetailsViewModel newInstance(SavedStateHandle savedStateHandle,
      DeleteProductUseCase deleteProductUseCase) {
    return new ProductDetailsViewModel(savedStateHandle, deleteProductUseCase);
  }
}
