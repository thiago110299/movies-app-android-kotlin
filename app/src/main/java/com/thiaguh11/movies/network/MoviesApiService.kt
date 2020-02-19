package com.thiaguh11.movies.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.thiaguh11.movies.models.Movie
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val BASE_URL = "https://api.themoviedb.org/3/movie"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface MoviesApiService {
    @GET("/popular")
    suspend fun getPopularMovies(@Query("api-key") apiKey: String, @Query("language") language: String) : List<Movie>
}

object MoviesApi {
    val retrofitService = retrofit.create(MoviesApiService::class.java)
}