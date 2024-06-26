package ru.dushkina.application.domain

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.dushkina.application.data.MainRepository
import ru.dushkina.application.data.PreferenceProvider
import ru.dushkina.application.data.TmdbApi
import ru.dushkina.application.data.entity.API
import ru.dushkina.application.data.entity.Film
import ru.dushkina.application.data.entity.TmdbResultsDto
import ru.dushkina.application.utils.Converter
import ru.dushkina.application.viewmodel.HomeFragmentViewModel


class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi,
                 private val preferences: PreferenceProvider
) {
    //В конструктор мы будем передавать коллбэк из вью модели, чтобы реагировать на то, когда фильмы будут получены
    //и страницу, которую нужно загрузить (это для пагинации)
    fun getFilmsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        //Метод getDefaultCategoryFromPreferences() будет нам получать при каждом запросе нужный нам список фильмов
        retrofitService.getFilms(getDefaultCategoryFromPreferences(), API.apiKey, "ru-RU", page).enqueue(object :
            Callback<TmdbResultsDto> {
            override fun onResponse(call: Call<TmdbResultsDto>, response: Response<TmdbResultsDto>) {
                //При успехе мы вызываем метод передаем onSuccess и в этот коллбэк список фильмов
                val list = Converter.convertApiListToDtoList(response.body()?.tmdbFilms)
                //Кладем фильмы в БД
                    repo.putToDb(list)
                callback.onSuccess()
            }

            override fun onFailure(call: Call<TmdbResultsDto>, t: Throwable) {
                //В случае провала вызываем другой метод коллбека
                callback.onFailure()
            }
        })
    }
    fun getFilmsFromDB(): LiveData<List<Film>> = repo.getAllFromDB()

    //Метод для сохранения настроек
    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }
    //Метод для получения настроек
    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()
}
