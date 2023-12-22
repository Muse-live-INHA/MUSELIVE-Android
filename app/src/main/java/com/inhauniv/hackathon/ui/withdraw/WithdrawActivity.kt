package com.inhauniv.hackathon.ui.payment.amount

import android.os.Bundle
import androidx.activity.viewModels
import com.inhauniv.hackathon.R
import com.inhauniv.hackathon.databinding.ActivityAmountBinding
import com.inhauniv.hackathon.domain.entity.Payment
import com.inhauniv.hackathon.domain.entity.WithDraw
import com.inhauniv.hackathon.ui.base.BaseActivity

class WithdrawActivity: BaseActivity<ActivityAmountBinding>(R.layout.activity_withdraw) {
    private val viewModel by viewModels<WithdrawViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val serviceId: Int = intent.getIntExtra("serviceId", 0)
        intent.getStringExtra("serviceName").also {
            binding.tvServiceName.text = it
        }

        binding.tvCancelBtn.setOnClickListener { finish() }
        binding.tvAmountBtn.setOnClickListener {
            val request = WithDraw(
                paymentAmount = Integer.parseInt(binding.etPaymentAmount.text.toString()),
                schoolId = 12191590
            )
            viewModel.postWithdraw(request)
        }

    }
}