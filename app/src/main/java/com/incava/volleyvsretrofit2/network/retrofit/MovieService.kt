package com.incava.volleyvsretrofit2.network.retrofit


import com.incava.volleyvsretrofit2.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
    fun requestMovieList(@Query("key") key : String, @Query("targetDt") targetDt : String) : Call<MovieResponse>
}