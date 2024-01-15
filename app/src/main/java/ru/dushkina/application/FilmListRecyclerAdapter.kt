package ru.dushkina.application

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBindings
import ru.dushkina.application.databinding.ActivityDetailsBinding
import ru.dushkina.application.databinding.ActivityMainBinding
import ru.dushkina.application.databinding.FilmItemBinding

class FilmListRecyclerAdapter(private val clickListener:OnItemClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<Film>()

    private var _binding: FilmItemBinding? = null
    private val binding get() = _binding!!
    fun onCreate(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FilmItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FilmViewHolder(FilmItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FilmViewHolder -> {

                holder.bind(items[position])
                binding.itemContainer.setOnClickListener {
                    clickListener.click(items[position])
                }
            }
        }
    }
    fun addItems (list: List<Film>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun click(film: Film)
    }
}