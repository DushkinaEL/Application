// Generated by Dagger (https://dagger.dev).
package ru.dushkina.remote_module;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import okhttp3.OkHttpClient;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class RemoteModule_ProvideOkHttpClientFactory implements Factory<OkHttpClient> {
  private final RemoteModule module;

  public RemoteModule_ProvideOkHttpClientFactory(RemoteModule module) {
    this.module = module;
  }

  @Override
  public OkHttpClient get() {
    return provideOkHttpClient(module);
  }

  public static RemoteModule_ProvideOkHttpClientFactory create(RemoteModule module) {
    return new RemoteModule_ProvideOkHttpClientFactory(module);
  }

  public static OkHttpClient provideOkHttpClient(RemoteModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideOkHttpClient());
  }
}