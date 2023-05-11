package com.example.my_netflix

import android.graphics.drawable.LayerDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my_netflix.databinding.ActivityMovieBinding
import com.example.my_netflix.model.Movie

class MovieActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtTittle.text = "Batman Begins"
        binding.txtMovieDesc.text = "Essa é a descrição do filme do Batman"
        binding.txtMovieCast.text = getString(R.string.cast,"Ator A, Ator B, Atriz C")

        actionBar()

        setMovieImage()

        val rvSimilar :RecyclerView = findViewById(R.id.rv_similar)
        rvSimilar.layoutManager = GridLayoutManager(this, 3)

        val movieList = mutableListOf<Movie>()
        for (i in 0 until 15) {
            val movie = Movie(R.drawable.movie_4)
            movieList.add((movie))
        }

        rvSimilar.adapter = MovieAdapter(movieList, R.layout.movie_item_similar)
    }

    private fun actionBar(){
        val toolbar: Toolbar = findViewById(R.id.movie_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null
    }

    private fun setMovieImage(){
        val layerDrawable: LayerDrawable =
            ContextCompat.getDrawable(this, R.drawable.shadow) as LayerDrawable

        val movieCover = ContextCompat.getDrawable(this, R.drawable.movie_4)

        layerDrawable.setDrawableByLayerId(R.id.cover_drawable, movieCover)

        val img : ImageView = findViewById(R.id.img_movie)
        img.setImageDrawable(layerDrawable)
    }
}