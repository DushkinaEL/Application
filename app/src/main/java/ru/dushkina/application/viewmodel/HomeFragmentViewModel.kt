package ru.dushkina.application.viewmodel



import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ru.dushkina.application.App
import ru.dushkina.application.data.Entity.Film
import ru.dushkina.application.domain.Interactor
import javax.inject.Inject

class HomeFragmentViewModel: ViewModel() {


    //Инициализируем интерактор
    @Inject
    lateinit var interactor: Interactor

    val filmsListData: Observable<List<Film>>
    val showProgressBar: BehaviorSubject<Boolean>
    init {
        App.instance.dagger.inject(this)
        showProgressBar = interactor.progressBarState
        filmsListData = interactor.getFilmsFromDB()
        getFilms()
    }
    fun getFilms() {
        interactor.getFilmsFromApi(1)

    }
    fun getSearchResult(search: String) = interactor.getSearchResultFromApi(search)
}