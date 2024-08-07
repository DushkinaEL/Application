package ru.dushkina.application.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.dushkina.application.view.rv_adapters.FilmListRecyclerAdapter
import ru.dushkina.application.view.MainActivity
import ru.dushkina.application.view.rv_adapters.TopSpacingItemDecoration
import ru.dushkina.application.databinding.FragmentHomeBinding
import ru.dushkina.application.data.Entity.Film
import ru.dushkina.application.utils.AnimationHelper
import ru.dushkina.application.utils.AutoDisposable
import ru.dushkina.application.utils.addTo
import ru.dushkina.application.viewmodel.HomeFragmentViewModel
import java.util.Locale

class HomeFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(HomeFragmentViewModel::class.java)
    }
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!
    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private val autoDisposable = AutoDisposable()

    private var filmsDataBase = listOf<Film>()
        //Используем backing field
        set(value) {
            //Если придет такое же значение, то мы выходим из метода
            if (field == value) return
            //Если пришло другое значение, то кладем его в переменную
            field = value
            //Обновляем RV адаптер
            filmsAdapter.addItems(field)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        autoDisposable.bindTo(lifecycle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        view?.setOnClickListener {
            val a = activity as FragmentActivity
            a.supportFragmentManager.beginTransaction()
                .replace(com.google.android.material.R.id.container, DetailsFragment())
                .addToBackStack("MainFragment").commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnimationHelper.performFragmentCircularRevealAnimation(
            binding.homeFragmentRoot,
            requireActivity(),
            1
        )

        initSearchView()
        initPullToRefresh()
        initRecycler()
        //Кладем БД в RV

        viewModel.filmsListData
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                filmsAdapter.addItems(list)
                filmsDataBase = list

            }
            .addTo(autoDisposable)

    viewModel.showProgressBar
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe{
        binding.progressBar.isVisible = it
    }
    .addTo(autoDisposable)
}


    fun initPullToRefresh() {
        //Вешаем слушатель, чтобы вызвался pull to refresh
        binding.pullToRefresh.setOnRefreshListener {
            //Чистим адаптер(items нужно будет сделать паблик или создать для этого публичный метод)
            filmsAdapter.items.clear()
            //Делаем новый запрос фильмов на сервер
            viewModel.getFilms()
            //Убираем крутящееся колечко
            binding.pullToRefresh.isRefreshing = false
        }
    }

    fun initSearchView() {
        with(binding) {
            // Для работы при нажатии на поле для поиска
            searchView.setOnClickListener {
                searchView.isIconified = false
            }
            //Подключаем слушателя изменений введенного текста в поле для поиска
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                //Этот метод отрабатывает при нажатии кнопки "поиск" на софт клавиатуре
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                //Этот метод отрабатывает на каждое изменение текста
                override fun onQueryTextChange(newText: String): Boolean {

                    //Если ввод пуст, то вставляем в адаптер всю БД
                    if (newText.isEmpty()) {
                        filmsAdapter.addItems(filmsDataBase)
                        return true
                    }
                    //Фильтруем список на поиск подходящих сочетаний
                    val result = filmsDataBase.filter {
                        //Приводим запрос и имя фильма к нижнему регистру
                        it.title.toLowerCase(Locale.getDefault()).contains(
                            newText.toLowerCase(
                                Locale.getDefault()
                            )
                        )
                    }
                    //Добавляем в адаптер
                    filmsAdapter.addItems(result)
                    return true
                }
            })
        }
    }