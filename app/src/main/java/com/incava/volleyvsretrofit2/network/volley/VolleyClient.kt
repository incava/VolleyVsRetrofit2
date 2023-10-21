package com.incava.volleyvsretrofit2.network.volley

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.incava.volleyvsretrofit2.util.CalenderUtil
import com.incava.volleyvsretrofit2.util.MovieInfo

object VolleyClient {
    private var instance: RequestQueue? = null

    fun getInstance(context: Context): RequestQueue {
        // RequestQueue를 받아오기 위한 메서드
        return instance ?: Volley.newRequestQueue(context)
    }

    fun getRestURL(url: String): String {
        // GET쿼리문을 RestAPI URL로 만들어 리턴
        // 필요한 키와 파라미터를 구현한 맵
        var movieParams = mapOf(
            "key" to MovieInfo.MOVIE_API_KEY,
            "targetDt" to CalenderUtil.getDateYesterdayForamtyyyymmdd(),
        )

        val dataParse = movieParams.entries.joinToString("&") { (key, value) -> "$key=$value" }
        return "$url?$dataParse"
    }

}