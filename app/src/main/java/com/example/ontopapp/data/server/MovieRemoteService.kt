package com.example.ontopapp.data.server

import com.example.ontopapp.data.server.remotemodel.MovieRemoteResult
import retrofit2.http.GET
import retrofit2.http.Header

interface MovieRemoteService {

    @GET("movie/popular?language=en-US")
    suspend fun listPopularMovies(
        @Header("Authorization") apiKey: String,
    ): MovieRemoteResult
}
