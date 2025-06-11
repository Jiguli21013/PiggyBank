package com.yanchelenko.piggybank.features.product_insert.presentation;

import androidx.lifecycle.SavedStateHandle;
import com.yanchelenko.piggybank.domain.usecases.GetPricePerKgUseCase;
import com.yanchelenko.piggybank.features.product_insert.domain.usecases.GetProductByBarcodeUseCase;
import com.yanchelenko.piggybank.features.product_insert.domain.usecases.InsertNewProductUseCase;
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
public final class InsertProductScreenViewModel_Factory implements Factory<InsertProductScreenViewModel> {
  private final Provider<InsertNewProductUseCase> insertNewProductUseCaseProvider;

  private final Provider<GetProductByBarcodeUseCase> getProductByBarcodeUseCaseProvider;

  private final Provider<GetPricePerKgUseCase> getPricePerKgUseCaseProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public InsertProductScreenViewModel_Factory(
      Provider<InsertNewProductUseCase> insertNewProductUseCaseProvider,
      Provider<GetProductByBarcodeUseCase> getProductByBarcodeUseCaseProvider,
      Provider<GetPricePerKgUseCase> getPricePerKgUseCaseProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.insertNewProductUseCaseProvider = insertNewProductUseCaseProvider;
    this.getProductByBarcodeUseCaseProvider = getProductByBarcodeUseCaseProvider;
    this.getPricePerKgUseCaseProvider = getPricePerKgUseCaseProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public InsertProductScreenViewModel get() {
    return newInstance(insertNewProductUseCaseProvider.get(), getProductByBarcodeUseCaseProvider.get(), getPricePerKgUseCaseProvider.get(), savedStateHandleProvider.get());
  }

  public static InsertProductScreenViewModel_Factory create(
      Provider<InsertNewProductUseCase> insertNewProductUseCaseProvider,
      Provider<GetProductByBarcodeUseCase> getProductByBarcodeUseCaseProvider,
      Provider<GetPricePerKgUseCase> getPricePerKgUseCaseProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new InsertProductScreenViewModel_Factory(insertNewProductUseCaseProvider, getProductByBarcodeUseCaseProvider, getPricePerKgUseCaseProvider, savedStateHandleProvider);
  }

  public static InsertProductScreenViewModel newInstance(
      InsertNewProductUseCase insertNewProductUseCase,
      GetProductByBarcodeUseCase getProductByBarcodeUseCase,
      GetPricePerKgUseCase getPricePerKgUseCase, SavedStateHandle savedStateHandle) {
    return new InsertProductScreenViewModel(insertNewProductUseCase, getProductByBarcodeUseCase, getPricePerKgUseCase, savedStateHandle);
  }
}
