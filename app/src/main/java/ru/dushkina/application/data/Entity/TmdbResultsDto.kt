package ru.dushkina.application.data.Entity

import com.google.gson.annotations.SerializedName
import ru.dushkina.application.data.Entity.TmdbFilm

data class TmdbResultsDto(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val tmdbFilms: List<TmdbFilm>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)