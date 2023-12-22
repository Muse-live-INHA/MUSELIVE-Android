package com.inhauniv.hackathon.domain.entity

import com.inhauniv.hackathon.domain.util.Empty

data class UserInfo(
    val all_month_payment: List<AllMonthPayment>,
    val balance: Int,
    val donate_payment: Int,
    val specific_month_donate_payment: Int,
    val specific_month_payment: List<SpecificMonthPayment>,
    val user_name: String
) {
    companion object {
        val EMPTY = UserInfo(
            all_month_payment = emptyList(),
            balance = 0,
            donate_payment = 0,
            specific_month_donate_payment = 0,
            specific_month_payment = emptyList(),
            user_name = String.Empty,
        )
    }
}

data class AllMonthPayment(
    val payment_amount: Int,
    val payment_datetime: String,
    val service_name: String
)

data class SpecificMonthPayment(
    val payment_amount: Int,
    val payment_datetime: String,
    val service_name: String
)