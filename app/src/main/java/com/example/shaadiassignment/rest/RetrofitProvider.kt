package com.example.shaadiassignment.rest

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//https://randomuser.me/api/?results=10

object RetrofitProvider {

    var retrofit: Retrofit? = null
        get() {

            if (field == null) {
                val client = OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build()

                field = Retrofit.Builder().baseUrl("https://randomuser.me/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client).build()
            }

            return field

        }

    private set

}