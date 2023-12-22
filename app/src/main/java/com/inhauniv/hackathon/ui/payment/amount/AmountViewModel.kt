package com.inhauniv.hackathon.ui.payment.amount

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.inhauniv.hackathon.data.repository.InduckpayRepository
import com.inhauniv.hackathon.data.repository.UserRepository
import com.inhauniv.hackathon.domain.entity.Item
import com.inhauniv.hackathon.domain.entity.Payment
import com.inhauniv.hackathon.domain.util.Empty
import com.inhauniv.hackathon.ui.MainViewModel
import com.inhauniv.hackathon.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AmountViewModel: BaseViewModel() {
    private val induckpayRepository = InduckpayRepository()
    fun postPayment(request: Payment) {
        viewModelScope.launch {
            kotlin.runCatching {
                induckpayRepository.postPayment(request)
            }.onSuccess { response ->
                Log.d("AmountViewModel", "response : $response")
            }.onFailure { error ->
                Log.e("AmountViewModel", "error : $error")
                sendError(error.message)
            }
        }
    }
}