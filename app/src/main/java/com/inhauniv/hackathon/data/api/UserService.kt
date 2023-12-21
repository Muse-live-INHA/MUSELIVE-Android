package com.inhauniv.hackathon.data.api

import com.inhauniv.hackathon.domain.entity.User
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("users")
    suspend fun getUser(
        @Query("q") q: String? = "kimhyeing"
    ): User
}