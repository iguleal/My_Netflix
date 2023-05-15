package com.example.my_netflix

import android.util.Log
import com.example.my_netflix.model.Category
import com.example.my_netflix.model.Movie
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

class CategoryTask {

    fun execute(url: String) {
        val executor = Executors.newSingleThreadExecutor()

        executor.execute {
            var urlConnection : HttpsURLConnection? = null
            var stream: InputStream? = null

            try {
                val requestURL = URL(url)
                urlConnection = requestURL.openConnection() as HttpsURLConnection
                urlConnection.readTimeout = 2000
                urlConnection.connectTimeout = 2000

                val statusCode: Int = urlConnection.responseCode
                if (statusCode > 400) {
                    throw IOException("erro na comunicação com o servidor!")
                }

                stream = urlConnection.inputStream
                val jsonAsString = stream.bufferedReader().use { it.readText() }

                toCategories(jsonAsString)


            } catch (e: IOException) {
                Log.e("teste", e.message ?: "erro desconhecido", e)
            } finally {
                urlConnection?.disconnect()
                stream?.close()
            }
        }
    }

    private fun toCategories(jsonAsString: String): List<Category> {
        val categories = mutableListOf<Category>()

        val jsonRoot = JSONObject(jsonAsString)
        val jsonCategories = jsonRoot.getJSONArray("category")
        for (i in 0 until jsonCategories.length()) {
            val jsonCategory = jsonCategories.getJSONObject(i)

            val title = jsonCategory.getString("title")
            val jsonMovies = jsonCategory.getJSONArray("movie")

            val movies = mutableListOf<Movie>()
            for (j in 0 until jsonMovies.length()) {
                val jsonMovie = jsonMovies.getJSONObject(j)
                val id = jsonMovie.getInt("id")
                val coverUrl = jsonMovie.getString("cover_url")

                movies.add(Movie(id, coverUrl))
            }
            categories.add(Category(title, movies))
        }
        return categories
    }
}