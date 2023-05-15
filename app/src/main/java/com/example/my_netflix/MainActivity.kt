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

        rvMain.adapter = CategoryAdapter(categoryList)

        CategoryTask().execute("https://api.tiagoaguiar.co/netflixapp/home?apiKey=20e64b98-2643-4bac-8224-401f0e29a83e")
    }
}
