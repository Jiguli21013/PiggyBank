package com.yanchelenko.piggybank.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0007\u0010\bJ\b\u0010\t\u001a\u00020\u0006H\u0007\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\n"}, d2 = {"Lcom/yanchelenko/piggybank/di/DispatchersModule;", "", "()V", "provideAppDispatchers", "Lcom/yanchelneko/piggybank/common/core_utils/dispatchers/AppDispatchers;", "provider", "Lcom/yanchelneko/piggybank/common/core_utils/dispatchers/AppDispatchers$Provider;", "provideAppDispatchers-lIP70qU", "(Lcom/yanchelneko/piggybank/common/core_utils/dispatchers/AppDispatchers$Provider;)Lcom/yanchelneko/piggybank/common/core_utils/dispatchers/AppDispatchers$Provider;", "provideDispatchersProvider", "di_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class DispatchersModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.yanchelenko.piggybank.di.DispatchersModule INSTANCE = null;
    
    private DispatchersModule() {
        super();
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.yanchelneko.piggybank.common.core_utils.dispatchers.AppDispatchers.Provider provideDispatchersProvider() {
        return null;
    }
}