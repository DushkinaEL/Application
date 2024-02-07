package ru.dushkina.application

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.dushkina.application.databinding.ActivityMainBinding
import ru.dushkina.application.databinding.FilmItemBinding

class FilmViewHolder(private val binding: FilmItemBinding) : RecyclerView.ViewHolder(binding.root) {
    private val title = binding.title
    private val poster = binding.poster
    private val description = binding.description

    fun bind (film: Film) {
        title.text = film.title
        //Указываем контейнер, в котором будет "жить" наша картинка
        Glide.with(itemView)
        //Загружаем сам ресурс
            .load(film.poster)
        //Центруем изображение
            .centerCrop()
            //Указываем ImageView, куда будем загружать изображение
            .into(poster)
        description.text = film.description
    }
}