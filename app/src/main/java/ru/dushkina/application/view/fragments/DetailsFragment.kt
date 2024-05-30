package ru.dushkina.application.view.fragments

import android.content.Intent
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.dushkina.application.R
import ru.dushkina.application.data.ApiConstants
import ru.dushkina.application.databinding.FragmentDetailsBinding
import ru.dushkina.application.data.entity.Film

class DetailsFragment : Fragment(R.layout.fragment_details) {
    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding!!
    private lateinit var  film: Film

    init {
        enterTransition = Slide(Gravity.END).apply { duration = 800 }
        returnTransition = Slide(Gravity.END).apply { duration = 800; mode = Slide.MODE_OUT }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFilmsDetails()
        binding.detailsFabFavorites.setOnClickListener{
            if (!film.isInFavorites) {
                binding.detailsFabFavorites.setImageResource(R.drawable.baseline_favorite_)
                film.isInFavorites = true
            } else {
                binding.detailsFabFavorites.setImageResource(R.drawable.baseline_favorite_border_24)
                film.isInFavorites = false
            }
        }
        binding.detailsFabShare.setOnClickListener{
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Check out this film: ${film.title} \n\n ${film.description}"
            )
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share To:"))
        }
    }

    private fun setFilmsDetails() {
         film = arguments?.get("film") as Film

        Glide.with(this)
            .load(ApiConstants.IMAGES_URL + "w780" + film.poster)
            .centerCrop()
            .into(binding.detailsPoster)

        with(binding) {
            detailsToolbar.title = film.title
            detailsDescription.text = film.description
            detailsFabFavorites.setImageResource(
                if (film.isInFavorites) R.drawable.baseline_favorite_
                else R.drawable.baseline_favorite_border_24
            )
        }
    }
}
