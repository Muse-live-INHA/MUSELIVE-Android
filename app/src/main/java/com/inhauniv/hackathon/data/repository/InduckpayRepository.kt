package com.inhauniv.hackathon.data.repository

import com.inhauniv.hackathon.data.ServiceCreator
import com.inhauniv.hackathon.domain.entity.Deposit
import com.inhauniv.hackathon.domain.entity.Payment
import com.inhauniv.hackathon.domain.entity.UserInfo
import com.inhauniv.hackathon.domain.entity.WithDraw

class InduckpayRepository {
    private val induckpayService = ServiceCreator.induckpayService

    suspend fun postDeposit(deposit: Deposit) {
        return induckpayService.postDeposit(deposit)
    }

     suspend fun postPayment(payment: Payment) {
        return induckpayService.postPayment(payment)
    }

     suspend fun postWithdraw(withDraw: WithDraw) {
        return induckpayService.postWithdraw(withDraw)
    }

    suspend fun getUserInfo(schoolId: Int, date: String): UserInfo{
        return induckpayService.getUserInfo(schoolId, date)
    }
}