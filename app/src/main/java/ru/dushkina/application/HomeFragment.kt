package ru.dushkina.application


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.dushkina.application.databinding.FragmentHomeBinding
import java.util.Locale

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!
    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    private val filmsDataBase = listOf(
        Film(
            "Leon",
            R.drawable.poster_5,
            "12-летнюю Матильду неохотно принимает Леон, профессиональный убийца, после того, как ее семья была убита. Необычные отношения складываются, когда она становится его протеже и осваивает ремесло убийцы.", 8.9f
        ),
        Film(
            "Se7en",
            R.drawable.poster_7,
            "Два детектива, новичок и ветеран, выслеживают серийного убийцу, мотивом которого являются семь смертных грехов.",6.6f
        ),
        Film(
            "Coco",
            R.drawable.poster_14,
            "Начинающий музыкант Мигель, столкнувшись с наследственным запретом на музыку в своей семье, отправляется в Страну Мертвых, чтобы найти своего прапрадеда, легендарного певца.",8.4f
        ),
        Film(
            "Forrest Gump",
            R.drawable.poster_13,
            "История Соединенных Штатов с 1950-х по 70-е годы разворачивается с точки зрения жителя Алабамы с IQ 75, который жаждет воссоединения со своей возлюбленной детства.",3.4f
        ),
        Film(
            "Joker",
            R.drawable.poster_12,
            "В 1980-е годы неудавшийся стендап-комик сходит с ума и ведет преступную и хаосную жизнь в Готэм-сити, становясь при этом печально известным криминальным психопатом.",9.4f
        ),
        Film(
            "Parasite",
            R.drawable.poster_11,
            "Жадность и классовая дискриминация угрожают недавно сформировавшимся симбиотическим отношениям между богатой семьей Пак и обездоленным кланом Кимов.",7.3f
        ),
        Film(
            "1+1",
            R.drawable.poster_10,
            "После того, как он стал парализованным из-за несчастного случая при полете на параплане, аристократ нанимает молодого человека из проекта в качестве его опекуна.",8.4f
        ),
        Film(
            "Pulp Fiction",
            R.drawable.poster_6,
            "Жизни двух наемных убийц, боксера, гангстера и его жены, а также пары бандитов из закусочной переплетаются в четырех историях о насилии и искуплении.", 3.2f
        ),
        Film(
            "Spider-Man: Across the Spider-Verse",
            R.drawable.poster_8,
            "Майлз Моралес катапультируется через мультивселенную, где встречает команду Людей-Пауков, которой поручено защищать само ее существование. Когда герои спорят о том, как справиться с новой угрозой, Майлзу приходится по-новому определить, что значит быть героем.",8.6f
        ),
        Film(
            "The Godfather",
            R.drawable.poster_9,
            "Дон Вито Корлеоне, глава мафиозной семьи, решает передать свою империю младшему сыну Майклу. Однако его решение непреднамеренно подвергает жизни его близких серьезной опасности.", 2.1f
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        view?.setOnClickListener{
            val a = activity as FragmentActivity
            a.supportFragmentManager.beginTransaction().replace(com.google.android.material.R.id.container, DetailsFragment()).addToBackStack("MainFragment").commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnimationHelper.performFragmentCircularRevealAnimation(binding.homeFragmentRoot,  requireActivity(), 1)

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
                //Находим наш RV
                initRecycler()
                //Кладем БД в RV
                filmsAdapter.addItems(filmsDataBase)


        }
    }
        private fun initRecycler() {
            binding.mainRecycler.apply {
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

