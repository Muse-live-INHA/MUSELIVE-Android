package com.inhauniv.hackathon.ui.home

import android.content.Intent
import android.os.Bundle
import com.inhauniv.hackathon.R
import com.inhauniv.hackathon.databinding.ActivityHomeBinding
import com.inhauniv.hackathon.domain.entity.PaymentDetail
import com.inhauniv.hackathon.ui.base.BaseActivity
import com.inhauniv.hackathon.ui.payment.pr.QrScanActivity

class HomeActivity: BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
    private val adapter by lazy { PaymentDetailAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAdapter()
        initView()
        observe()
    }

    private fun initAdapter() {
        binding.rvPaymentDetails.itemAnimator = null
        binding.rvPaymentDetails.adapter = adapter
    }
    private fun initView() {
        binding.tvPayment.setOnClickListener {
            val intent = Intent(this, QrScanActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observe() {
        val list = listOf<PaymentDetail>(PaymentDetail("자동 충전"), PaymentDetail("충전"), PaymentDetail("송금"), PaymentDetail("입금"), PaymentDetail("유경뿡") )
        adapter.submitList(list)
    }
}