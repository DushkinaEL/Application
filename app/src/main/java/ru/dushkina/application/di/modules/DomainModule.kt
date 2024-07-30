package ru.dushkina.application.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.dushkina.application.data.MainRepository
import ru.dushkina.application.data.preferenes.PreferenceProvider
import ru.dushkina.application.data.TmdbApi
import ru.dushkina.application.domain.Interactor
import javax.inject.Singleton

@Module
//Передаем контекст для SharedPreferences через конструктор
class DomainModule (val context: Context){
    //Нам нужно контекст как-то провайдить, поэтому создаем такой метод
    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    //Создаем экземпляр SharedPreferences
    fun providePreferences(context: Context) = PreferenceProvider(context)
    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository, tmdbApi: TmdbApi, preferenceProvider: PreferenceProvider) = Interactor(repo = repository, retrofitService = tmdbApi, preferences = preferenceProvider)
}