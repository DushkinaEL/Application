package ru.dushkina.application.domain

import ru.dushkina.application.data.MainRepository

class Interactor(val repo: MainRepository) {
    fun getFilmsDB(): List<Film> = repo.filmsDataBase
}