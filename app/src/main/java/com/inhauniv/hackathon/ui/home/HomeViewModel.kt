package com.inhauniv.hackathon.ui.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.inhauniv.hackathon.data.repository.InduckpayRepository
import com.inhauniv.hackathon.domain.entity.Item
import com.inhauniv.hackathon.domain.entity.UserInfo
import com.inhauniv.hackathon.domain.util.Empty
import com.inhauniv.hackathon.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel: BaseViewModel() {
    private val induckpayRepository = InduckpayRepository()

    private val _info: MutableStateFlow<UserInfo> = MutableStateFlow(UserInfo.EMPTY)
    val info: StateFlow<UserInfo>
        get() = _info

    fun getUserInfo(schoolId: Int, date: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                induckpayRepository.getUserInfo(schoolId, date)
            }.onSuccess { response ->
                Log.d(TAG, "response : $response")
                _info.value = response
            }.onFailure { error ->
                Log.e(TAG, "error : $error")
                sendError(error.message)
            }
        }
    }

    companion object {
        const val TAG = "HomeViewModel"
    }
}