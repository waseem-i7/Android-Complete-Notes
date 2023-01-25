package com.mwi7.newsbook

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "https://newsapi.org/v2/"

    private val client = OkHttpClient.Builder().build()

    private  val gson = GsonBuilder().create()

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

    fun buildService() : NewsService{
        return  retrofit.create(NewsService::class.java)
    }

}