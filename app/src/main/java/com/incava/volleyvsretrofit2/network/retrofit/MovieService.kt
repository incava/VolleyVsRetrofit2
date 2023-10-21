package com.incava.volleyvsretrofit2.network.retrofit


import com.incava.volleyvsretrofit2.model.MovieResponse
import com.incava.volleyvsretrofit2.util.MovieInfo.REST_DAILY_MOVIE_BOX_OFFICE_URL
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET(REST_DAILY_MOVIE_BOX_OFFICE_URL)
    fun requestMovieList(@Query("key") key : String, @Query("targetDt") targetDt : String) : Call<MovieResponse>
}