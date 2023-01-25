package com.mwi7.newsbook

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    fun getNews(@Query("country")country : String , @Query("apiKey")apiKey : String ) : Call<NewsResponse>
}