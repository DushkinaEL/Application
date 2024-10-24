package ru.dushkina.application

import android.app.Application
import ru.dushkina.application.di.AppComponent
import ru.dushkina.application.di.modules.DatabaseModule
import ru.dushkina.application.di.modules.DomainModule
import ru.dushkina.remote_module.DaggerRemoteComponent
import ru.dushkina.application.di.DaggerAppComponent




class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        //Создаем компонент
        val remoteProvider = DaggerRemoteComponent.create()
        dagger = DaggerAppComponent.builder()
            .remoteProvider(remoteProvider)
            .databaseModule(DatabaseModule())
            .domainModule(DomainModule(this))
            .build()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}