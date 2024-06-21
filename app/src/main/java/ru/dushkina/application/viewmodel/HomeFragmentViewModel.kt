package ru.dushkina.application.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import ru.dushkina.application.App
import ru.dushkina.application.data.Entity.Film
import ru.dushkina.application.domain.Interactor
import javax.inject.Inject

class HomeFragmentViewModel: ViewModel() {
    //Инициализируем интерактор
    @Inject
    lateinit var interactor: Interactor
    val filmsListData: Flow<List<Film>>
    val showProgressBar: Channel<Boolean>
    init {
        App.instance.dagger.inject(this)
        showProgressBar = interactor.progressBarState
        filmsListData = interactor.getFilmsFromDB()
        getFilms()
    }
    fun getFilms() {
        interactor.getFilmsFromApi(1)
    }
}