package ru.dushkina.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

        initButtons()
        }

private fun initButtons(){
    button_menu.setOnClickListener {
    Toast.makeText(this, "Меню", Toast.LENGTH_SHORT).show()
}
    button_favorite.setOnClickListener {
        Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
    }
    button_watckLater.setOnClickListener {
        Toast.makeText(this, "Посмотреть позже", Toast.LENGTH_SHORT).show()
    }
    button_selection.setOnClickListener {
        Toast.makeText(this, "Подборки", Toast.LENGTH_SHORT).show()
    }

    button_settings.setOnClickListener {
        Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
    }

}
}

