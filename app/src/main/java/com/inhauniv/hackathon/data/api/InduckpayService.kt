package com.inhauniv.hackathon.data.api

import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param
import com.inhauniv.hackathon.domain.entity.Deposit
import com.inhauniv.hackathon.domain.entity.Payment
import com.inhauniv.hackathon.domain.entity.UserInfo
import com.inhauniv.hackathon.domain.entity.WithDraw
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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

    @GET("userInfo")
    suspend fun getUserInfo(
        @Query("school_id") schoolId: Int,
        @Query("date_info") dateInfo: String,
    ): UserInfo
}