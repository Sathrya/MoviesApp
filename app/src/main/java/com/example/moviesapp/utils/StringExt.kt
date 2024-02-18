package com.example.moviesapp.utils

fun String.convertToCurrency(str : String): String {
        val stringBuilder = StringBuilder(str)
        var index = 3
        while (index < stringBuilder.length) {
            stringBuilder.insert(index, ",")
            index += 3 + 1 // Increment index by n + 1 to account for the inserted character
        }
        return "$stringBuilder USD"
}

fun String.show2decimals(voteAverage: Double?): String {
    return String.format("%.1f",voteAverage)
}