package ru.dushkina.remote_module;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J6\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\u00062\b\b\u0001\u0010\t\u001a\u00020\nH\'J6\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020\u00062\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\t\u001a\u00020\nH\'\u00a8\u0006\r"}, d2 = {"Lru/dushkina/remote_module/TmdbApi;", "", "getFilmFromSearch", "Lio/reactivex/rxjava3/core/Observable;", "Lru/dushkina/remote_module/entity/TmdbResults;", "apiKey", "", "language", "query", "page", "", "getFilms", "category", "remote_module_debug"})
public abstract interface TmdbApi {
    
    @retrofit2.http.GET(value = "3/movie/{category}")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.rxjava3.core.Observable<ru.dushkina.remote_module.entity.TmdbResults> getFilms(@retrofit2.http.Path(value = "category")
    @org.jetbrains.annotations.NotNull
    java.lang.String category, @retrofit2.http.Query(value = "api_key")
    @org.jetbrains.annotations.NotNull
    java.lang.String apiKey, @retrofit2.http.Query(value = "language")
    @org.jetbrains.annotations.NotNull
    java.lang.String language, @retrofit2.http.Query(value = "page")
    int page);
    
    @retrofit2.http.GET(value = "3/search/movie")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.rxjava3.core.Observable<ru.dushkina.remote_module.entity.TmdbResults> getFilmFromSearch(@retrofit2.http.Query(value = "api_key")
    @org.jetbrains.annotations.NotNull
    java.lang.String apiKey, @retrofit2.http.Query(value = "language")
    @org.jetbrains.annotations.NotNull
    java.lang.String language, @retrofit2.http.Query(value = "query")
    @org.jetbrains.annotations.NotNull
    java.lang.String query, @retrofit2.http.Query(value = "page")
    int page);
}