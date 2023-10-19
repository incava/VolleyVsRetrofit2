package com.incava.volleyvsretrofit2.network.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val BASE_URL = "http://www.kobis.or.kr/"

    private var instance : Retrofit? = null

    fun getInstance() : Retrofit {
        return instance ?: Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}