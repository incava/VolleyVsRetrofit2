package com.incava.volleyvsretrofit2.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.incava.volleyvsretrofit2.adapter.MovieAdapter
import com.incava.volleyvsretrofit2.databinding.ActivityMainBinding
import com.incava.volleyvsretrofit2.model.MovieDTO
import com.incava.volleyvsretrofit2.model.MovieResponse
import com.incava.volleyvsretrofit2.network.retrofit.MovieService
import com.incava.volleyvsretrofit2.network.retrofit.RetrofitClient
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
        // Todo Volley 라이브러리를 통해 api 로드

    }

    fun loadAPIWithRetrofit2() {
        // Retrofit2 통해 api 로드
        val retrofit = RetrofitClient.getInstance().create(MovieService::class.java)
        retrofit.requestMovieList(key = "4333ddda436b647e7303848bb887344d", targetDt = "20230611")
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        var result: MovieResponse? = response.body()
                        Log.d("result", result.toString())
                        result?.boxOfficeResult?.dailyBoxOfficeList?.toMutableList()?.apply {
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