package com.incava.volleyvsretrofit2.util

import android.widget.ImageView
import com.bumptech.glide.Glide

object Number {

    // 억 단위 까지 관객 수 계
    fun formatNumber(number: Long): String {
        val stringBuilder = StringBuilder()
        val absNumber = Math.abs(number)

        if (absNumber >= 100000000) {
            val akk  = absNumber / 100000000
            stringBuilder.append(akk).append("억")
        }

        val man = absNumber % 100000000 / 10000
        if (man > 0) {
            if (stringBuilder.isNotEmpty()) {
                stringBuilder.append(" ")
            }
            stringBuilder.append(man).append("만")
        }

        val rest = absNumber % 10000
        if (rest > 0) {
            if (stringBuilder.isNotEmpty()) {
                stringBuilder.append(" ")
            }
            stringBuilder.append(rest)
        }

        return if (number < 0) {
            "-$stringBuilder"
        } else {
            stringBuilder.toString()
        }
    }

    private fun getPicNumber(rank: String): NumberPicName {
        return when (rank) {
            "1" -> NumberPicName.ONE
            "2" -> NumberPicName.TWO
            "3" -> NumberPicName.THREE
            "4" -> NumberPicName.FOUR
            "5" -> NumberPicName.FIVE
            "6" -> NumberPicName.SIX
            "7" -> NumberPicName.SEVEN
            "8" -> NumberPicName.EIGHT
            "9" -> NumberPicName.NINE
            "10" -> NumberPicName.TEN
            else -> throw IllegalArgumentException("Invalid rank: $rank")
        }
    }

    fun showImageForNumber(rank : String, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(getPicNumber(rank).drawableResId)
            .into(imageView)
    }



}