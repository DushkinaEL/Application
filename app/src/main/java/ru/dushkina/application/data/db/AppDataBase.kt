package ru.dushkina.application.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.dushkina.application.data.DAO.FilmDao
import ru.dushkina.application.data.Entity.Film

@Database(entities = [Film::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}