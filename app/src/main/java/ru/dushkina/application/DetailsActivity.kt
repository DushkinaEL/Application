package ru.dushkina.application

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.dushkina.application.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity(R.layout.activity_details) {
    private var _binding: ActivityDetailsBinding? = null
    private val binding get() = _binding!!
     fun onCreate(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ActivityDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setFilmsDetails() {
        val film = intent.extras?.get("film") as Film

        binding.detailsToolbar.title = film.title
        binding.detailsPoster.setImageResource(film.poster)
        binding.detailsDescription.text = film.description
    }
}
