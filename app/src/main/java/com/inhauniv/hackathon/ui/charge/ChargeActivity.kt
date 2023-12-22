package com.inhauniv.hackathon.ui.charge

import android.os.Bundle
import androidx.activity.viewModels
import com.inhauniv.hackathon.R
import com.inhauniv.hackathon.databinding.ActivityChargeBinding
import com.inhauniv.hackathon.domain.entity.Deposit
import com.inhauniv.hackathon.ui.base.BaseActivity

class ChargeActivity: BaseActivity<ActivityChargeBinding>(R.layout.activity_charge) {
    private val viewModel by viewModels<ChargeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvCancelBtn.setOnClickListener { finish() }
        binding.tvChargeBtn.setOnClickListener {
            val request = Deposit(
                paymentAmount = Integer.parseInt(binding.etChargeAmount.text.toString()),
                schoolId = 12191590,
            )
            viewModel.postDeposit(request)
        }

    }
}