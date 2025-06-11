package com.yanchelenko.piggybank.di;

import com.yanchelneko.piggybank.common.core_utils.dispatchers.AppDispatchers;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class DispatchersModule_ProvideDispatchersProviderFactory implements Factory<AppDispatchers.Provider> {
  @Override
  public AppDispatchers.Provider get() {
    return provideDispatchersProvider();
  }

  public static DispatchersModule_ProvideDispatchersProviderFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AppDispatchers.Provider provideDispatchersProvider() {
    return Preconditions.checkNotNullFromProvides(DispatchersModule.INSTANCE.provideDispatchersProvider());
  }

  private static final class InstanceHolder {
    private static final DispatchersModule_ProvideDispatchersProviderFactory INSTANCE = new DispatchersModule_ProvideDispatchersProviderFactory();
  }
}
