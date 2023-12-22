package com.inhauniv.hackathon.ui.withdraw

import android.os.Bundle
import androidx.activity.viewModels
import com.inhauniv.hackathon.R
import com.inhauniv.hackathon.databinding.ActivityWithdrawBinding
import com.inhauniv.hackathon.domain.entity.Deposit
import com.inhauniv.hackathon.domain.entity.WithDraw
import com.inhauniv.hackathon.domain.util.formatAmount
import com.inhauniv.hackathon.ui.base.BaseActivity

class WithdrawActivity: BaseActivity<ActivityWithdrawBinding>(R.layout.activity_withdraw) {
    private val viewModel by viewModels<WithdrawViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.getIntExtra("account", 0).also {
            binding.tvRemainAccount.text = formatAmount(it)
        }

        binding.tvCancelBtn.setOnClickListener { finish() }
        binding.tvWithdrawBtn.setOnClickListener {
            val request = WithDraw(
                paymentAmount = Integer.parseInt(binding.etWithdrawAmount.text.toString()),
                schoolId = 12181707
            )
            viewModel.postWithdraw(request)
            finish()
        }
    }
}