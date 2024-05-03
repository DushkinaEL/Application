package ru.dushkina.application.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.dushkina.application.data.MainRepository
import ru.dushkina.application.data.db.DatabaseHelper
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabaseHelper(context: Context) = DatabaseHelper(context)

    @Provides
    @Singleton
    fun provideRepository(databaseHelper: DatabaseHelper) = MainRepository(databaseHelper)
}