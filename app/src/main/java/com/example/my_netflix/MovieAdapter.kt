package com.example.my_netflix

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.my_netflix.model.Movie

class MovieAdapter(
    private val movieList: MutableList<Movie>,
    @LayoutRes val layoutId: Int) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            MovieAdapter.MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            val imgMovie: ImageView = itemView.findViewById(R.id.img_movie)

//            imgMovie.setImageResource(movie.coverUrl)
        }
    }
}