package com.example.shaadiassignment.rest

import androidx.lifecycle.LiveData
import com.example.shaadiassignment.db.entities.Match
import com.example.shaadiassignment.db.entities.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FetchService {

    @GET("api")
    suspend fun getMatches(@Query("results") numResults: Int) : Response

}