package com.badri.apiintegration.networking

import com.badri.apiintegration.model.JokesData
import retrofit2.http.GET


interface ApiService {
    @GET("jokes/random")
    suspend fun getJokes(): JokesData
}