package com.example.my_netflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my_netflix.model.Category
import com.example.my_netflix.model.Movie

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvMain: RecyclerView = findViewById(R.id.rv_main)
        rvMain.layoutManager = LinearLayoutManager(this)

        val categoryList = mutableListOf<Category>()

        for (j in 0 until 5) {
            val movieList = mutableListOf<Movie>()
            for (i in 0 until 10) {
                val movie = Movie(R.drawable.movie_4)
                movieList.add((movie))
            }
            val category = Category("category: $j", movieList)
            categoryList.add(category)
        }
        rvMain.adapter = CategoryAdapter(categoryList)
    }
}
