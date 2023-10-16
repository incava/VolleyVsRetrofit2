package com.incava.volleyvsretrofit2.model

import com.google.gson.annotations.SerializedName

/**
 *  영화 정보 Data Class
 *  https://www.kobis.or.kr/kobisopenapi/homepg/apiservice/searchServiceInfo.do
 */

data class MovieDTO(
    @SerializedName("movieNm")
    val movieNm : String, // 영화 제목
    @SerializedName("audiAcc")
    val audiAcc : String, // 누적 관객수
    @SerializedName("openDt")
    val openDt : String, // 개봉일
    @SerializedName("rank")
    val rank : String, // 영화 순위
)
