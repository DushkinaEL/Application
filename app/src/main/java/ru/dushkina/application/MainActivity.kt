package ru.dushkina.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
        }


     fun initButtonsMenu(view: View) {
            Toast.makeText(this, "Меню", Toast.LENGTH_SHORT).show()
        }
    fun initBtnFavorite(view: View){
            Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
        }
    fun initBtnWatchLater(view: View){
            Toast.makeText(this, "Посмотреть позже", Toast.LENGTH_SHORT).show()
        }
    fun initBtnSelection(view: View) {
            Toast.makeText(this, "Подборки", Toast.LENGTH_SHORT).show()
        }
    fun initBtnSettings(view: View){
            Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
        }
    }

