package com.inhauniv.hackathon.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.inhauniv.hackathon.R
import com.inhauniv.hackathon.databinding.ActivityMainBinding
import com.inhauniv.hackathon.domain.util.showToast
import com.inhauniv.hackathon.ui.base.BaseActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        observe()
    }

    private fun initView() {
        viewModel.getItems()
    }

    private fun observe() {
        viewModel.items.onEach { items ->
            if(items.isNotEmpty()) {
                Log.d(TAG, "items : $items")
                showToast(this, items[0].login)
            }
        }.launchIn(lifecycleScope)

        viewModel.error.onEach { error ->
            showToast(this, error)
        }.launchIn(lifecycleScope)
    }


    companion object {
        const val TAG = "MainActivity"
    }
}