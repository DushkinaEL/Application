package ru.dushkina.application.view.rv_viewholders


import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.dushkina.remote_module.entity.ApiConstants
import ru.dushkina.application.databinding.FilmItemBinding
import ru.dushkina.application.data.Entity.Film

class FilmViewHolder(private var binding: FilmItemBinding) : RecyclerView.ViewHolder(binding.root) {
    //Привязываем View из layout к переменным
    private val title = binding.title
    private val poster = binding.poster
    private val description = binding.description
    // Здесь мы находим в верстке наш прогресс бар для рейтинга
    private val ratingDonut = binding.ratingDonut

    // В этом методе кладем данные из film в наши view
    fun bind (film: Film) {
        // Устанавливаем заголовок
        title.text = film.title
        // Устанавливаем постер
        //Указываем контейнер, в котором будет "жить" наша картинка
        Glide.with(itemView)
        //Загружаем сам ресурс
            .load(ru.dushkina.remote_module.entity.ApiConstants.IMAGES_URL + "w342" + film.poster)
        //Центруем изображение
            .centerCrop()
            //Указываем ImageView, куда будем загружать изображение
            .into(poster)
        // Устанавливаем описание
        description.text = film.description
        // Устанавливаем рейтинг
        ratingDonut.setProgressAnimated((film.rating * 10 ).toInt())
    }
}

