package ru.dushkina.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import ru.dushkina.application.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initButtons()
        }

private fun initButtons(){
    binding.buttonMenu.setOnClickListener {
    Toast.makeText(this, "Меню", Toast.LENGTH_SHORT).show()
}
    binding.buttonFavorite.setOnClickListener {
        Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
    }
    binding.buttonWatchLater.setOnClickListener {
        Toast.makeText(this, "Посмотреть позже", Toast.LENGTH_SHORT).show()
    }
    binding.buttonSelection.setOnClickListener {
        Toast.makeText(this, "Подборки", Toast.LENGTH_SHORT).show()
    }

    binding.buttonSettings.setOnClickListener {
        Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
    }

}
}

