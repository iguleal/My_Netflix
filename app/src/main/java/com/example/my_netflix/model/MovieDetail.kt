package com.example.my_netflix.model

data class MovieDetail(
    val movie: Movie,
    val similar: List<Movie>
)
