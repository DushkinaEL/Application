package ru.dushkina.application.view


import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import ru.dushkina.application.R
import ru.dushkina.application.databinding.ActivityMainBinding
import ru.dushkina.application.data.Entity.Film
import ru.dushkina.application.utils.ConnectionChecker
import ru.dushkina.application.view.fragments.DetailsFragment
import ru.dushkina.application.view.fragments.FavoriteFragment
import ru.dushkina.application.view.fragments.HomeFragment
import ru.dushkina.application.view.fragments.SelectionsFragment
import ru.dushkina.application.view.fragments.SettingsFragment
import ru.dushkina.application.view.fragments.WatchLaterFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var receiver: BroadcastReceiver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        receiver = ConnectionChecker()
        val filters = IntentFilter().apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_BATTERY_LOW)
        }
        registerReceiver(receiver, filters)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, HomeFragment())
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

                    R.id.settings -> {
                        val tag = "settings"
                        val fragment = checkFragmentExistence(tag)
                        changeFragment(fragment?: SettingsFragment(), tag)
                        true
                    }

                    else -> false
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
    //Ищем фрагмент по тегу, если он есть возвращаем его, если нет , то null
    fun checkFragmentExistence(tag: String): Fragment? =
        supportFragmentManager.findFragmentByTag(tag)

    fun changeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment, tag)
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
            .replace(R.id.fragment_container, fragment)
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



