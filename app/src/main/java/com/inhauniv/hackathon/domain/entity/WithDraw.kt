package com.inhauniv.hackathon.domain.entity

import com.google.gson.annotations.SerializedName

data class WithDraw(
    @SerializedName("school_id")
    val schoolId: Int,
    @SerializedName("payment_amount")
    val paymentAmount: Int
)
