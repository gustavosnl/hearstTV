package com.glima.data.repository

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
sealed class ApiResponse<T>(
    val data: T? = null,
    val message: String? = null,
) {
    @JsonClass(generateAdapter = true)
    class Success<T>(data: T) : ApiResponse<T>(data)

    @JsonClass(generateAdapter = true)
    class Error<T>(message: String?, data: T? = null) : ApiResponse<T>(data, message)
}