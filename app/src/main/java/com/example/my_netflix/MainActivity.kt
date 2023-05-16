package com.example.my_netflix

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my_netflix.model.Category

class MainActivity : AppCompatActivity(), CategoryTask.Callback {

    private lateinit var progressBar: ProgressBar
    private val categoryList = mutableListOf<Category>()
    private lateinit var adapter : CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)

        val rvMain: RecyclerView = findViewById(R.id.rv_main)
        rvMain.layoutManager = LinearLayoutManager(this)

        adapter = CategoryAdapter(categoryList) { id ->
            val i = Intent(this, MovieActivity::class.java)
            i.putExtra("id", id)
            startActivity(i)
        }
        rvMain.adapter = adapter

        CategoryTask(this).execute("https://api.tiagoaguiar.co/netflixapp/home?apiKey=20e64b98-2643-4bac-8224-401f0e29a83e")
    }

    override fun onResult(categories: List<Category>) {
        categoryList.clear()
        categoryList.addAll(categories)
        adapter.notifyDataSetChanged()
        progressBar.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        progressBar.visibility = View.GONE
    }

    override fun preExecute() {
        progressBar.visibility = View.VISIBLE
    }
}
