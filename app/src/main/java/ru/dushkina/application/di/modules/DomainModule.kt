package ru.dushkina.application.di.modules

import dagger.Module
import dagger.Provides
import ru.dushkina.application.data.MainRepository
import ru.dushkina.application.data.TmdbApi
import ru.dushkina.application.domain.Interactor
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository, tmdbApi: TmdbApi) = Interactor(repo = repository, retrofitService = tmdbApi)
}