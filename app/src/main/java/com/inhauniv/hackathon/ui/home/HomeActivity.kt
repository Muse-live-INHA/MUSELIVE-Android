package com.inhauniv.hackathon.ui.home

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.inhauniv.hackathon.R
import com.inhauniv.hackathon.databinding.ActivityHomeBinding
import com.inhauniv.hackathon.domain.entity.PaymentDetail
import com.inhauniv.hackathon.domain.util.formatAmount
import com.inhauniv.hackathon.ui.base.BaseActivity
import com.inhauniv.hackathon.ui.charge.ChargeActivity
import com.inhauniv.hackathon.ui.payment.pr.QrScanActivity
import com.inhauniv.hackathon.ui.withdraw.WithdrawActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeActivity: BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
    private val adapter by lazy { PaymentDetailAdapter() }
    private val viewModel by viewModels<HomeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getFCMToken()
        initAdapter()
        initView()
        observe()
    }
    override fun onStart() {
        super.onStart()
        initView()
        observe()
    }
    override fun onResume() {
        super.onResume()
        initView()
        observe()
    }

    private fun getFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.e(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            Log.d(TAG, "FCM token : $token")
        })
    }
    private fun initAdapter() {
        binding.rvPaymentDetails.itemAnimator = null
        binding.rvPaymentDetails.adapter = adapter
    }
    private fun initView() {
        viewModel.getUserInfo(12181707, "2023-12")
        binding.tvPayment.setOnClickListener {
            val intent = Intent(this, QrScanActivity::class.java)
            startActivity(intent)
        }

        binding.tvDeposit.setOnClickListener {
            val intent = Intent(this, ChargeActivity::class.java)
            startActivity(intent)
        }

        try {
            binding.tvWithdraw.setOnClickListener {
                val intent = Intent(this, WithdrawActivity::class.java)
                intent.putExtra("account", viewModel.info.value.balance)
                startActivity(intent)
            }
        } catch (e: Exception) {
            Log.e("HomeActivity", "tvWithdraw 에러 : ${e.toString()}")
        }

    }

    private fun observe() {
        viewModel.info.onEach { info ->
            binding.availAmount.text = formatAmount(info.balance)
            binding.tvTotalDonationAmount.text = formatAmount(info.donate_payment)
            binding.tvMonthDonationAmount.text = formatAmount(info.specific_month_donate_payment)
            binding.tvUser.text = info.user_name+"님의 페이"
            adapter.submitList(info.specific_month_payment)
        }.launchIn(lifecycleScope)
    }
}