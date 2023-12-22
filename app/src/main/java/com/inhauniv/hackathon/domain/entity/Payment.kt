package com.inhauniv.hackathon.domain.entity

import com.google.gson.annotations.SerializedName

data class Payment(
    @SerializedName("school_id")
    val schoolId: Int,
    @SerializedName("service_id")
    val serviceId: Int,
    @SerializedName("payment_amount")
    val paymentAmount: Int
)
