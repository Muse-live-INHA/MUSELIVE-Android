package com.inhauniv.hackathon.ui.payment.pr

import android.content.Intent
import android.os.Bundle
import android.util.Log
import java.util.Base64
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.inhauniv.hackathon.R
import com.inhauniv.hackathon.databinding.ActivityQrscanBinding
import com.inhauniv.hackathon.domain.util.showToast
import com.inhauniv.hackathon.ui.base.BaseActivity
import com.inhauniv.hackathon.ui.payment.amount.AmountActivity
import org.json.JSONObject

class QrScanActivity: BaseActivity<ActivityQrscanBinding>(R.layout.activity_qrscan) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initQRcodeScanner()
    }

    private fun initQRcodeScanner() {
        val integrator  = IntentIntegrator(this)
        integrator.setBeepEnabled(false)
        integrator.setOrientationLocked(true)
        integrator.setPrompt("QR 코드를 촬영해주세요")
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result: IntentResult =
            IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result.contents == null) {
            // qr코드 값 없거나, 뒤로가기 클릭 시
            Log.e(TAG, "QR결제 실패 : 값 x")
            showToast(this@QrScanActivity, "결제가 취소되었습니다.")
            finish()
        } else {
            try {
                Log.d(TAG, "QR코드 스캔 성공 : ${result.contents}")
                val jsonObject = JSONObject(result.contents)
                // service_id 값 추출
                val serviceId = jsonObject.getInt("service_id")
                Log.d(TAG, "Service ID: $serviceId")

                // service_name 값 추출
                val serviceName = jsonObject.getString("service_name")
                Log.d(TAG, "Service Name: $serviceName")

                binding.tvData.text = "serviceId : $serviceId\n servicName : $serviceName"
                // 금액 입력 화면 이동으로 추후 변경
                val intent = Intent(this@QrScanActivity, AmountActivity::class.java)
                intent.putExtra("serviceId", serviceId)
                intent.putExtra("serviceName", serviceName)
                startActivity(intent)
                finish()
            }
            catch (e: Exception) {
                Log.e(TAG, e.message.toString())
            }
        }
    }

    companion object {
        const val TAG = "QrScanActivity"
    }
}