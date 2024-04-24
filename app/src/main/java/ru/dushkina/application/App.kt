package ru.dushkina.application

import android.app.Application
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import com.airbnb.lottie.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dushkina.application.data.ApiConstants
import ru.dushkina.application.data.MainRepository
import ru.dushkina.application.data.TmdbApi
import ru.dushkina.application.di.AppComponent
import ru.dushkina.application.di.DaggerAppComponent
import ru.dushkina.application.domain.Interactor
import java.util.concurrent.TimeUnit

class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        //Создаем компонент
        dagger = DaggerAppComponent.create()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}