package com.incava.volleyvsretrofit2.model

data class MovieResponse(
    val boxOfficeResult: BoxOfficeResult
)

data class BoxOfficeResult(
    val boxofficeType: String,
    val showRange: String,
    val dailyBoxOfficeList: List<MovieDTO>
)