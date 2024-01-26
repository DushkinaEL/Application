package ru.dushkina.application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ru.dushkina.application.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var filmsAdapter: FilmListRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val filmsDataBase = listOf(
            Film ("Леон", R.drawable.poster_5, "12-летнюю Матильду неохотно принимает Леон, профессиональный убийца, после того, как ее семья была убита. Необычные отношения складываются, когда она становится его протеже и осваивает ремесло убийцы."),
            Film("Семь", R.drawable.poster_7, "Два детектива, новичок и ветеран, выслеживают серийного убийцу, мотивом которого являются семь смертных грехов."),
            Film("Тайна Коко", R.drawable.poster_14, "Начинающий музыкант Мигель, столкнувшись с наследственным запретом на музыку в своей семье, отправляется в Страну Мертвых, чтобы найти своего прапрадеда, легендарного певца."),
            Film("Форест Гамп", R.drawable.poster_13, "История Соединенных Штатов с 1950-х по 70-е годы разворачивается с точки зрения жителя Алабамы с IQ 75, который жаждет воссоединения со своей возлюбленной детства."),
            Film("Джокер", R.drawable.poster_12, "В 1980-е годы неудавшийся стендап-комик сходит с ума и ведет преступную и хаосную жизнь в Готэм-сити, становясь при этом печально известным криминальным психопатом."),
            Film("Паразиты", R.drawable.poster_11, "Жадность и классовая дискриминация угрожают недавно сформировавшимся симбиотическим отношениям между богатой семьей Пак и обездоленным кланом Кимов."),
            Film("1+1", R.drawable.poster_10, "После того, как он стал парализованным из-за несчастного случая при полете на параплане, аристократ нанимает молодого человека из проекта в качестве его опекуна."),
            Film("Криминальное чтиво", R.drawable.poster_6, "Жизни двух наемных убийц, боксера, гангстера и его жены, а также пары бандитов из закусочной переплетаются в четырех историях о насилии и искуплении."),
            Film("Человек_Паук: Паутина Вселенных", R.drawable.poster_8, "Майлз Моралес катапультируется через мультивселенную, где встречает команду Людей-Пауков, которой поручено защищать само ее существование. Когда герои спорят о том, как справиться с новой угрозой, Майлзу приходится по-новому определить, что значит быть героем."),
            Film("Крестный отец", R.drawable.poster_9, "Дон Вито Корлеоне, глава мафиозной семьи, решает передать свою империю младшему сыну Майклу. Однако его решение непреднамеренно подвергает жизни его близких серьезной опасности.")
        )


            binding.mainRecycler.apply {

            filmsAdapter = FilmListRecyclerAdapter (object : FilmListRecyclerAdapter.OnItemClickListener {
                override fun click(film: Film) {
                    val bundle = Bundle()
                    bundle.putParcelable("film", film)
                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
            })
                adapter = filmsAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
                val decorator = TopSpacingItemDecoration(8)
                addItemDecoration(decorator)
        }
            filmsAdapter.addItems(filmsDataBase)



        binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.settings -> {
                    Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }

        binding.topAppBar.setNavigationOnClickListener {
            Toast.makeText(this, "Когда-нибудь здесь будет навигация...", Toast.LENGTH_SHORT).show()
        }


        binding.bottomNavigation.setOnNavigationItemSelectedListener() {

            when (it.itemId) {
                R.id.favorites -> {
                    Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.watch_later -> {
                    Toast.makeText(this, "Посмотреть позже", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.selections -> {
                    Toast.makeText(this, "Подборки", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }
    }
}


