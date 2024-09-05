package ru.dushkina.remote_module

import ru.dushkina.remote_module.TmdbApi

interface RemoteProvider {
    fun provideRemote(): TmdbApi
}