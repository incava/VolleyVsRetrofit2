package com.incava.volleyvsretrofit2.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import com.incava.volleyvsretrofit2.adapter.MovieAdapter
import com.incava.volleyvsretrofit2.databinding.ActivityMainBinding
import com.incava.volleyvsretrofit2.model.MovieDTO
import com.incava.volleyvsretrofit2.model.MovieResponse
import com.incava.volleyvsretrofit2.network.retrofit.MovieService
import com.incava.volleyvsretrofit2.network.retrofit.RetrofitClient
import com.incava.volleyvsretrofit2.network.volley.VolleyClient
import com.incava.volleyvsretrofit2.network.volley.VolleyClient.getRestURL
import com.incava.volleyvsretrofit2.util.CalenderUtil
import com.incava.volleyvsretrofit2.util.MovieInfo.BASE_URL
import com.incava.volleyvsretrofit2.util.MovieInfo.MOVIE_API_KEY
import com.incava.volleyvsretrofit2.util.MovieInfo.REST_DAILY_MOVIE_BOX_OFFICE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var movieList = mutableListOf<MovieDTO>()

    lateinit var movieAdapter: MovieAdapter

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initSetOnclickListener()
        initSetRecyclerViewAdapter()
    }

    private fun initSetOnclickListener() {
        // 클릭 리스너 붙이는 메서드
        binding.apply {
            btnVolley.setOnClickListener { loadAPIWithVolley() }
            btnRetrofit2.setOnClickListener { loadAPIWithRetrofit2() }
            btnClearList.setOnClickListener { clearAPIRecyclerView() }
        }
    }

    private fun initSetRecyclerViewAdapter() {
        // RecyclerView Setting
        movieAdapter = MovieAdapter(movieList)
        binding.rcvMovieList.apply {
            adapter = movieAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    fun loadAPIWithVolley() {
        // Volley 통해 api 로드
        //  요청사항 만들기 - GET환경, url + query값 전달
        val requestStartTime = System.currentTimeMillis()
        val stringRequest = StringRequest(
            Request.Method.GET,
            getRestURL("$BASE_URL$REST_DAILY_MOVIE_BOX_OFFICE_URL"),
            { response ->
                val responseTime = System.currentTimeMillis() - requestStartTime
                Log.d("APIResponseTime","Volley : $responseTime")
                val movieResponse = Gson().fromJson(response, MovieResponse::class.java)
                movieResponse?.boxOfficeResult?.dailyBoxOfficeList?.toMutableList()?.apply {
                    movieList.clear()
                    movieList.addAll(this)
                    // 어댑터 갱신
                    movieAdapter.notifyDataSetChanged()
                }
            },
            { error ->
                Log.d("Volley is Fail", "${error.message}")
            }
        )
        VolleyClient.getInstance(this@MainActivity).add(stringRequest)
    }

    fun loadAPIWithRetrofit2() {
        // Retrofit2 통해 api 로드
        val retrofit = RetrofitClient.getInstance().create(MovieService::class.java)
        val requestStartTime = System.currentTimeMillis()
        retrofit.requestMovieList(key = MOVIE_API_KEY, targetDt = CalenderUtil.getDateYesterdayForamtyyyymmdd())
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    val responseTime = System.currentTimeMillis() - requestStartTime
                    Log.d("APIResponseTime","Retrofit2 : $responseTime")
                    if (response.isSuccessful) {

                        var result: MovieResponse? = response.body()
                        Log.d("result", result.toString())
                        result?.boxOfficeResult?.dailyBoxOfficeList?.toMutableList()?.apply {
                            Log.d("result", this.toString())
                            movieList.clear()
                            movieList.addAll(this)
                            // 어댑터 갱신
                            movieAdapter.notifyDataSetChanged()
                        }
                    } else {
                        Log.d("result", "failed")
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d("Retrofit is Fail", "${t.message}")
                }
            })
    }

    fun clearAPIRecyclerView() {
        // 리사이클러뷰가 담긴 리사이클러뷰를 제거
        // Todo 리사이클러뷰의 리스트를 제거
        movieList.clear()
        // 어댑터 갱신
        movieAdapter.notifyDataSetChanged()
    }


}