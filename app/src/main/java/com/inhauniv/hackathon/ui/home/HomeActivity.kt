package com.inhauniv.hackathon.ui.home

import android.content.Intent
import android.os.Bundle
import com.inhauniv.hackathon.R
import com.inhauniv.hackathon.databinding.ActivityHomeBinding
import com.inhauniv.hackathon.ui.base.BaseActivity
import com.inhauniv.hackathon.ui.payment.pr.QrScanActivity

class HomeActivity: BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.tvPayment.setOnClickListener {
            val intent = Intent(this, QrScanActivity::class.java)
            startActivity(intent)
        }
    }
}