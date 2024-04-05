package ru.dushkina.application.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dushkina.application.App
import ru.dushkina.application.domain.Film
import ru.dushkina.application.domain.Interactor

class HomeFragmentViewModel: ViewModel() {
    val filmsListLiveData = MutableLiveData<List<Film>>()
    //Инициализируем интерактор
    private var interactor: Interactor = App.instance.interactor
    init {
        val films = interactor.getFilmsDB()
        filmsListLiveData.postValue(films)
    }
}