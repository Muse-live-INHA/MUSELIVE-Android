package com.inhauniv.hackathon.data.api

import com.inhauniv.hackathon.domain.entity.Deposit
import com.inhauniv.hackathon.domain.entity.Payment
import com.inhauniv.hackathon.domain.entity.WithDraw
import retrofit2.http.Body
import retrofit2.http.POST

interface InduckpayService {
    @POST("deposit")
    suspend fun postDeposit(
        @Body request: Deposit
    )

    @POST("payment")
    suspend fun postPayment(
        @Body request: Payment
    )


    @POST("payment")
    suspend fun postWithdraw(
        @Body request: WithDraw
    )
}