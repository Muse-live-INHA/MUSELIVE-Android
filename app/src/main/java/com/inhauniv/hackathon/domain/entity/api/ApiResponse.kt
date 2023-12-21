package com.inhauniv.muselive.domain.entity.api

data class ApiResponse<T>(
    val data: T,
    val message: String,
    val code: String
)
