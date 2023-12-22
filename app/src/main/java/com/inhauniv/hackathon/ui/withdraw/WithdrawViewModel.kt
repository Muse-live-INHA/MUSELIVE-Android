package com.inhauniv.hackathon.ui.payment.amount

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.inhauniv.hackathon.data.repository.InduckpayRepository
import com.inhauniv.hackathon.data.repository.UserRepository
import com.inhauniv.hackathon.domain.entity.Item
import com.inhauniv.hackathon.domain.entity.Payment
import com.inhauniv.hackathon.domain.entity.WithDraw
import com.inhauniv.hackathon.domain.util.Empty
import com.inhauniv.hackathon.ui.MainViewModel
import com.inhauniv.hackathon.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WithdrawViewModel: BaseViewModel() {
    private val induckpayRepository = InduckpayRepository()
    fun postWithdraw(request: WithDraw) {
        viewModelScope.launch {
            kotlin.runCatching {
                induckpayRepository.postWithdraw(request)
            }.onSuccess { response ->
                Log.d("WithdrawViewModel", "response : $response")
            }.onFailure { error ->
                Log.e("WithdrawViewModel", "error : $error")
                sendError(error.message)
            }
        }
    }
}