package ru.dushkina.application.data


import kotlinx.coroutines.flow.Flow
import ru.dushkina.application.data.DAO.FilmDao
import ru.dushkina.application.data.Entity.Film


class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
        filmDao.insertAll(films)
    }

    fun getAllFromDB(): Flow<List<Film>> = filmDao.getCachedFilms()

}