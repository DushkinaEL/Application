package ru.dushkina.application.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dushkina.application.App
import ru.dushkina.application.data.entity.Film
import ru.dushkina.application.domain.Interactor
import javax.inject.Inject

class HomeFragmentViewModel: ViewModel() {
    val showProgressBar: MutableLiveData<Boolean> = MutableLiveData()
    //Инициализируем интерактор


    @Inject
    lateinit var interactor: Interactor
    val filmsListLiveData: LiveData<List<Film>>
    init {
        App.instance.dagger.inject(this)
        filmsListLiveData = interactor.getFilmsFromDB()
        getFilms()
    }
    fun getFilms() {
        showProgressBar.postValue(true)
        interactor.getFilmsFromApi(1, object : ApiCallback{
            override fun onSuccess() {
                showProgressBar.postValue(false)
            }
            override fun onFailure() {

                showProgressBar.postValue(false)
            }
        })
    }

    interface ApiCallback {
        fun onSuccess()
        fun onFailure()
        
    }
}