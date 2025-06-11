package com.yanchelenko.piggybank.features.product_edit.presentation;

import androidx.lifecycle.SavedStateHandle;
import com.yanchelenko.piggybank.domain.usecases.GetPricePerKgUseCase;
import com.yanchelenko.piggybank.domain.usecases.GetProductByIdUseCase;
import com.yanchelenko.piggybank.features.product_edit.domain.usecases.UpdateProductUseCase;
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
public final class EditProductViewModel_Factory implements Factory<EditProductViewModel> {
  private final Provider<UpdateProductUseCase> updateProductUseCaseProvider;

  private final Provider<GetProductByIdUseCase> getProductByProductIdUseCaseProvider;

  private final Provider<GetPricePerKgUseCase> getPricePerKgUseCaseProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public EditProductViewModel_Factory(Provider<UpdateProductUseCase> updateProductUseCaseProvider,
      Provider<GetProductByIdUseCase> getProductByProductIdUseCaseProvider,
      Provider<GetPricePerKgUseCase> getPricePerKgUseCaseProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.updateProductUseCaseProvider = updateProductUseCaseProvider;
    this.getProductByProductIdUseCaseProvider = getProductByProductIdUseCaseProvider;
    this.getPricePerKgUseCaseProvider = getPricePerKgUseCaseProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public EditProductViewModel get() {
    return newInstance(updateProductUseCaseProvider.get(), getProductByProductIdUseCaseProvider.get(), getPricePerKgUseCaseProvider.get(), savedStateHandleProvider.get());
  }

  public static EditProductViewModel_Factory create(
      Provider<UpdateProductUseCase> updateProductUseCaseProvider,
      Provider<GetProductByIdUseCase> getProductByProductIdUseCaseProvider,
      Provider<GetPricePerKgUseCase> getPricePerKgUseCaseProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new EditProductViewModel_Factory(updateProductUseCaseProvider, getProductByProductIdUseCaseProvider, getPricePerKgUseCaseProvider, savedStateHandleProvider);
  }

  public static EditProductViewModel newInstance(UpdateProductUseCase updateProductUseCase,
      GetProductByIdUseCase getProductByProductIdUseCase, GetPricePerKgUseCase getPricePerKgUseCase,
      SavedStateHandle savedStateHandle) {
    return new EditProductViewModel(updateProductUseCase, getProductByProductIdUseCase, getPricePerKgUseCase, savedStateHandle);
  }
}
