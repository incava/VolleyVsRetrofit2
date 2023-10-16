package com.incava.volleyvsretrofit2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.incava.volleyvsretrofit2.adapter.MovieAdapter
import com.incava.volleyvsretrofit2.databinding.ActivityMainBinding
import com.incava.volleyvsretrofit2.model.MovieDTO

class MainActivity : AppCompatActivity() {

    val dummyMovieList = mutableListOf<MovieDTO>(
        MovieDTO("천사와 악마11","1000","23-06-11","1"),
        MovieDTO("천사와 악마12","1001","23-06-12","2"),
        MovieDTO("천사와 악마13","1002","23-06-13","3"),
        MovieDTO("천사와 악마14","1003","23-06-14","4"),
        MovieDTO("천사와 악마15","1004","23-06-15","5"),
        MovieDTO("천사와 악마16","1005","23-06-16","6"),
        MovieDTO("천사와 악마17","1006","23-06-25","7"),
        MovieDTO("천사와 악마18","1007","23-07-15","8"),
        MovieDTO("천사와 악마19","1008","23-01-11","9"),
        MovieDTO("천사와 악마20","1009","23-02-12","10"),
        MovieDTO("천사와 악마22","10001111","23-03-13","11"),

    )

    lateinit var movieAdapter : MovieAdapter

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initSetOnclickListener()
        initSetRecyclerViewAdapter()
    }

    private fun initSetOnclickListener(){
        // 클릭 리스너 붙이는 메서드
        binding.apply {
            btnVolley.setOnClickListener { loadAPIWithVolley()  }
            btnRetrofit2.setOnClickListener {  loadAPIWithRetrofit2() }
            btnClearList.setOnClickListener { clearAPIRecyclerView() }
        }
    }

    private fun initSetRecyclerViewAdapter(){
        // RecyclerView Setting
        movieAdapter = MovieAdapter(dummyMovieList)
        binding.rcvMovieList.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
        }
    }

    fun loadAPIWithVolley(){
        // Volley 통해 api 로드
        // Todo Volley 라이브러리를 통해 api 로드
    }

    fun loadAPIWithRetrofit2(){
        // Retrofit2 통해 api 로드
        // Todo Retrofit2 라이브러리를 통해 api 로드
    }

    fun clearAPIRecyclerView(){
        // 리사이클러뷰가 담긴 리사이클러뷰를 제거
        // Todo 리사이클러뷰의 리스트를 제거

    }


}