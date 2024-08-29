package ru.dushkina.application.data


import android.database.Observable
import ru.dushkina.application.data.DAO.FilmDao
import ru.dushkina.application.data.Entity.Film


class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
        filmDao.insertAll(films)
    }

    fun getAllFromDB(): Observable<List<Film>> = filmDao.getCachedFilms()

}