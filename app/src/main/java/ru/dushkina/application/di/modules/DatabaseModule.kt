package ru.dushkina.application.di.modules

import dagger.Module
import dagger.Provides
import ru.dushkina.application.data.MainRepository
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideRepository() = MainRepository()
}