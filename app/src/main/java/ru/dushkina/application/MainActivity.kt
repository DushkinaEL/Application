package ru.dushkina.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import ru.dushkina.application.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment())
            .addToBackStack(null)
            .commit()
        
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
                        val tag = "favorites"
                        val fragment = checkFragmentExistence(tag)
                        // В первом параметре, если фрагмент не найден и метод вернул null, то с помощью элвиса мы вызываем создание нового фрагмента
                        changeFragment(fragment?: FavoriteFragment(), tag)
                        true
                    }

                    R.id.watch_later -> {
                        val tag = "watch_later"
                        val fragment = checkFragmentExistence(tag)
                        changeFragment(fragment?: WatchLaterFragment(), tag)
                        true
                    }

                    R.id.selections -> {
                        val tag = "selections"
                        val fragment = checkFragmentExistence(tag)
                        changeFragment(fragment?: SelectionsFragment(), tag)
                        true
                    }

                    R.id.home -> {
                        val tag = "home"
                        val fragment = checkFragmentExistence(tag)
                        changeFragment(fragment?: HomeFragment(), tag)
                        true
                    }

                    else -> false
                }
            }
    }
    //Ищем фрагмент по тегу, если он есть возвращаем его, если нет , то null
    fun checkFragmentExistence(tag: String): Fragment? =
        supportFragmentManager.findFragmentByTag(tag)

    fun changeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment, tag)
            .addToBackStack(null)
            .commit()
    }
    fun launchDetailsFragment(film: Film) {
        val bundle = Bundle()

        bundle.putParcelable("film", film)

        val fragment = DetailsFragment()

        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack(null)
            .commit()

    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 1){
            AlertDialog.Builder(this)
                .setMessage("Вы точно хотите выйти?")
                .setIcon(R.drawable.baseline_browse_gallery)
                .setNegativeButton("Нет"){dialog, id ->
                    dialog.dismiss()
                }
                .setPositiveButton("Да"){dialog, id ->
                    finish()
                }
                .show()
        }else{
            super.onBackPressed()
        }

    }
}


