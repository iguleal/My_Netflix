package com.example.my_netflix

import android.util.Log
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

                Log.i("Teste", jsonAsString)


            } catch (e: IOException) {
                Log.e("teste", e.message ?: "erro desconhecido", e)
            } finally {
                urlConnection?.disconnect()
                stream?.close()
            }
        }
    }
}