package com.inhauniv.hackathon.ui.charge

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.inhauniv.hackathon.data.repository.InduckpayRepository
import com.inhauniv.hackathon.domain.entity.Deposit
import com.inhauniv.hackathon.domain.entity.Payment
import com.inhauniv.hackathon.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class ChargeViewModel: BaseViewModel() {
    private val induckpayRepository = InduckpayRepository()
    fun postDeposit(request: Deposit) {
        viewModelScope.launch {
            kotlin.runCatching {
                induckpayRepository.postDeposit(request)
            }.onSuccess { response ->
                Log.d("ChargeViewModel", "response : $response")
            }.onFailure { error ->
                Log.e("ChargeViewModel", "error : $error")
                sendError(error.message)
            }
        }
    }
}