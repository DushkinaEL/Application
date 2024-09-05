package ru.dushkina.application.domain

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ru.dushkina.application.data.Entity.API
import ru.dushkina.application.data.MainRepository
import ru.dushkina.application.data.preferenes.PreferenceProvider
import ru.dushkina.remote_module.TmdbApi
import ru.dushkina.application.data.Entity.Film
import ru.dushkina.application.utils.Converter
import ru.dushkina.remote_module.entity.TmdbResults


class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi, private val preferences: PreferenceProvider) {
    var progressBarState: BehaviorSubject<Boolean> = BehaviorSubject.create()
    //В конструктор мы будем передавать коллбэк из вью модели, чтобы реагировать на то, когда фильмы будут получены
    //и страницу, которую нужно загрузить (это для пагинации)
    fun getFilmsFromApi(page: Int) {
        //Показываем ProgressBar
            progressBarState.onNext(true)
        //Метод getDefaultCategoryFromPreferences() будет нам получать при каждом запросе нужный нам список фильмов
        with(retrofitService) {
            getFilms(getDefaultCategoryFromPreferences(),
                apiKey = API.apiKey,
                language = "ru-RU",
                page = page
            )
                .subscribeOn(Schedulers.io())
                .map {
                    Converter.convertApiListToDtoList(it.tmdbFilms)
                }
                .subscribeBy(
                    onError = {
                        progressBarState.onNext(false)
                    },
                    onNext = {
                        progressBarState.onNext(false)
                        repo.putToDb(it)
                    }
                )
        }
    }
    fun getFilmsFromDB():Observable<List<Film>> = repo.getAllFromDB()

    fun getSearchResultFromApi(search: String): Observable<List<Film>> = retrofitService.getFilmFromSearch(
        apiKey = API.apiKey,
        language = "ru-RU",
        query = search,
        page = 1
    )
        .map {
            Converter.convertApiListToDtoList(it.tmdbFilms)

        }
    //Метод для сохранения настроек
    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }
    //Метод для получения настроек
    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()

}
