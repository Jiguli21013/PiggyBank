package com.yanchelenko.piggybank.fearues.history.presentation;

import com.yanchelenko.piggybank.domain.usecases.DeleteProductUseCase;
import com.yanchelenko.piggybank.fearues.history.domain.GetPagedProductsUseCase;
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
public final class HistoryViewModel_Factory implements Factory<HistoryViewModel> {
  private final Provider<GetPagedProductsUseCase> getPagedProductsUseCaseProvider;

  private final Provider<DeleteProductUseCase> deleteProductUseCaseProvider;

  public HistoryViewModel_Factory(Provider<GetPagedProductsUseCase> getPagedProductsUseCaseProvider,
      Provider<DeleteProductUseCase> deleteProductUseCaseProvider) {
    this.getPagedProductsUseCaseProvider = getPagedProductsUseCaseProvider;
    this.deleteProductUseCaseProvider = deleteProductUseCaseProvider;
  }

  @Override
  public HistoryViewModel get() {
    return newInstance(getPagedProductsUseCaseProvider.get(), deleteProductUseCaseProvider.get());
  }

  public static HistoryViewModel_Factory create(
      Provider<GetPagedProductsUseCase> getPagedProductsUseCaseProvider,
      Provider<DeleteProductUseCase> deleteProductUseCaseProvider) {
    return new HistoryViewModel_Factory(getPagedProductsUseCaseProvider, deleteProductUseCaseProvider);
  }

  public static HistoryViewModel newInstance(GetPagedProductsUseCase getPagedProductsUseCase,
      DeleteProductUseCase deleteProductUseCase) {
    return new HistoryViewModel(getPagedProductsUseCase, deleteProductUseCase);
  }
}
