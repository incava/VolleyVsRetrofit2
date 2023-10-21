package com.incava.volleyvsretrofit2.network.retrofit

import com.incava.volleyvsretrofit2.util.MovieInfo.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // 인스턴스를 만들어 싱글톤으로 사용.
    private var instance : Retrofit? = null

    // getInstance를 사용해 instance가 이미 있을 경우에는 그대로 사용.
    fun getInstance() : Retrofit {
        return instance ?: Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}