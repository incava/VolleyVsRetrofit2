package com.incava.volleyvsretrofit2.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.incava.volleyvsretrofit2.databinding.ItemMovieListBinding
import com.incava.volleyvsretrofit2.model.MovieDTO

/**
 * 영화 데이터를 리사이클러뷰로 보여주는 어댑터 클래스
 */

class MovieAdapter(private val dataSet : MutableList<MovieDTO>) :
    RecyclerView.Adapter<MovieAdapter.VH>() {

    class VH(private val binding: ItemMovieListBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: MovieDTO) {
            binding.apply {
                tvMovieNm.text = "제목명 : ${data.movieNm}"
                tvAudiAcc.text = "관객수 : ${data.audiAcc}"
                tvRank.text = "등수 : ${data.rank}"
                tvOpenDt.text = "개봉일 : ${data.openDt}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding =
            ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size
}