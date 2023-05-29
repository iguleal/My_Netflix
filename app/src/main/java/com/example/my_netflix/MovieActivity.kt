package com.example.my_netflix

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.my_netflix.databinding.ActivityMovieBinding
import com.example.my_netflix.model.MovieDetail
import com.squareup.picasso.Picasso
import java.io.IOException

class MovieActivity : AppCompatActivity(), MovieTask.Callback {

    private lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent?.extras?.getInt("id") ?: throw IOException("erro")

        val url =
            "https://api.tiagoaguiar.co/netflixapp/movie/$id?apiKey=20e64b98-2643-4bac-8224-401f0e29a83e"
        MovieTask(this).execute(url)

        actionBar()
    }

    override fun onResult(movieDetail: MovieDetail) {

        setMovieImage(movieDetail.movie.coverUrl)
        binding.txtTittle.text = movieDetail.movie.title
        binding.txtMovieDesc.text = movieDetail.movie.desc
        binding.txtMovieCast.text = getString(R.string.cast, movieDetail.movie.cast)

        val movieList = movieDetail.similar
        binding.rvSimilar.layoutManager = GridLayoutManager(this, 3)
        binding.rvSimilar.adapter = MovieAdapter(movieList, R.layout.movie_item_similar)

        binding.progressBar.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        binding.progressBar.visibility = View.GONE
    }

    override fun preExecute() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun actionBar() {
        val toolbar: Toolbar = findViewById(R.id.movie_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null
    }

    private fun setMovieImage(coverUrl: String) {

        Picasso.get().load(coverUrl).into(object : com.squareup.picasso.Target {

            override fun onBitmapFailed(e: java.lang.Exception?, errorDrawable: Drawable?) {

                val errorMessage: String = "Não foi possível carregar a imagem do filme!"
                Toast.makeText(this@MovieActivity, errorMessage, Toast.LENGTH_LONG).show()
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                val layerDrawable: LayerDrawable = ContextCompat.getDrawable(
                    this@MovieActivity,
                    R.drawable.shadow
                ) as LayerDrawable
                val movieCover = BitmapDrawable(resources, bitmap)
                layerDrawable.setDrawableByLayerId(R.id.cover_drawable, movieCover)

                binding.imgMovie.setImageDrawable(layerDrawable)
            }
        })
    }
}