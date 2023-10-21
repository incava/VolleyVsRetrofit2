package com.incava.volleyvsretrofit2.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object CalenderUtil {

    fun getDateYesterdayForamtyyyymmdd() :String {
        // 전날 날짜를 yyymmdd로 변환시켜 리턴합니다.
        val currentDate = LocalDate.now().minusDays(1)
        return currentDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
    }

}