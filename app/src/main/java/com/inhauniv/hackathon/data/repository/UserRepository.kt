package com.inhauniv.hackathon.data.repository

import com.inhauniv.hackathon.data.ServiceCreator
import com.inhauniv.hackathon.domain.entity.User

class UserRepository {
    private val userService = ServiceCreator.userService

    suspend fun getUser(): User {
        return userService.getUser()
    }
}