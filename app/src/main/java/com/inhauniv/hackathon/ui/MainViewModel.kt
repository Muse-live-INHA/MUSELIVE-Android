package com.inhauniv.hackathon.ui

import android.util.Log
import com.inhauniv.hackathon.data.repository.UserRepository
import com.inhauniv.hackathon.domain.entity.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import com.inhauniv.hackathon.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel: BaseViewModel() {
    private val userRepository = UserRepository()

    private val _items: MutableStateFlow<List<Item>> = MutableStateFlow(emptyList())
    val items: StateFlow<List<Item>>
        get() = _items

    fun getItems() {
        viewModelScope.launch {
            kotlin.runCatching {
                userRepository.getUser()
            }.onSuccess { response ->
                Log.d(TAG, "response : $response")
                _items.value = response.items
            }.onFailure { error ->
                Log.e(TAG, "error : $error")
                sendError(error.message)
            }
        }
    }

    companion object {
        const val TAG = "MainViewModel"
    }
}