package ru.dushkina.application

import android.content.Intent
import android.os.Bundle
import android.transition.Scene
import android.transition.Slide
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import ru.dushkina.application.databinding.FragmentHomeBinding
import ru.dushkina.application.databinding.MergeHomeScreenContentBinding
import java.util.Locale

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!
    private lateinit var bindingScene: MergeHomeScreenContentBinding
    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    private val filmsDataBase = listOf(
        Film(
            "Leon",
            R.drawable.poster_5,
            "12-летнюю Матильду неохотно принимает Леон, профессиональный убийца, после того, как ее семья была убита. Необычные отношения складываются, когда она становится его протеже и осваивает ремесло убийцы."
        ),
        Film(
            "Seven",
            R.drawable.poster_7,
            "Два детектива, новичок и ветеран, выслеживают серийного убийцу, мотивом которого являются семь смертных грехов."
        ),
        Film(
            "Coco",
            R.drawable.poster_14,
            "Начинающий музыкант Мигель, столкнувшись с наследственным запретом на музыку в своей семье, отправляется в Страну Мертвых, чтобы найти своего прапрадеда, легендарного певца."
        ),
        Film(
            "Forrest Gump",
            R.drawable.poster_13,
            "История Соединенных Штатов с 1950-х по 70-е годы разворачивается с точки зрения жителя Алабамы с IQ 75, который жаждет воссоединения со своей возлюбленной детства."
        ),
        Film(
            "Joker",
            R.drawable.poster_12,
            "В 1980-е годы неудавшийся стендап-комик сходит с ума и ведет преступную и хаосную жизнь в Готэм-сити, становясь при этом печально известным криминальным психопатом."
        ),
        Film(
            "Parasite",
            R.drawable.poster_11,
            "Жадность и классовая дискриминация угрожают недавно сформировавшимся симбиотическим отношениям между богатой семьей Пак и обездоленным кланом Кимов."
        ),
        Film(
            "1+1",
            R.drawable.poster_10,
            "После того, как он стал парализованным из-за несчастного случая при полете на параплане, аристократ нанимает молодого человека из проекта в качестве его опекуна."
        ),
        Film(
            "Pulp Fiction",
            R.drawable.poster_6,
            "Жизни двух наемных убийц, боксера, гангстера и его жены, а также пары бандитов из закусочной переплетаются в четырех историях о насилии и искуплении."
        ),
        Film(
            "Spider-Man: Across the Spider-Verse",
            R.drawable.poster_8,
            "Майлз Моралес катапультируется через мультивселенную, где встречает команду Людей-Пауков, которой поручено защищать само ее существование. Когда герои спорят о том, как справиться с новой угрозой, Майлзу приходится по-новому определить, что значит быть героем."
        ),
        Film(
            "The Godfather",
            R.drawable.poster_9,
            "Дон Вито Корлеоне, глава мафиозной семьи, решает передать свою империю младшему сыну Майклу. Однако его решение непреднамеренно подвергает жизни его близких серьезной опасности."
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingScene = MergeHomeScreenContentBinding.inflate(layoutInflater, binding.homeFragmentRoot, false)

            val scene = Scene(binding.homeFragmentRoot, bindingScene.root)
        //Создаем анимацию выезда поля поиска сверху
        val searchSlide = Slide(Gravity.TOP).addTarget(R.id.search_view)
        //Создаем анимацию выезда RV снизу
        val recyclerSlide = Slide (Gravity.BOTTOM).addTarget(R.id.main_recycler)
        //Создаем экземпляр TransitionSet, для объединения анимаций
        val customTransition = TransitionSet().apply {
            //Устанавливаем время анимации
            duration = 1000
            //Добавляем анимации
            addTransition(searchSlide)
            addTransition(recyclerSlide)
        }
        //Запускаем через TransitionManager, вторым параметром передаем нашу кастомную анимацию
        TransitionManager.go(scene, customTransition)

            with(bindingScene) {
            // Для работы при нажатии на поле для поиска
            searchView.setOnClickListener {
                searchView.isIconified = false
            }
            //Подключаем слушателя изменений введенного текста в поле для поиска
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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
            //Находим наш RV
            initRecycler()
            //Кладем БД в RV
            filmsAdapter.addItems(filmsDataBase)

        }
    }
        private fun initRecycler() {
            bindingScene.mainRecycler.apply {
                filmsAdapter =
                    FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                        override fun click(film: Film) {
                            (requireContext() as MainActivity).launchDetailsFragment(film)
                        }
                    })
                adapter = filmsAdapter
                layoutManager = LinearLayoutManager(requireContext())
                val decorator = TopSpacingItemDecoration(8)
                addItemDecoration(decorator)
            }
        }
    }

