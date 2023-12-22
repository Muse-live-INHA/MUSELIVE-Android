package com.inhauniv.hackathon.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inhauniv.hackathon.databinding.ItemPaymentPlusListBinding
import com.inhauniv.hackathon.domain.entity.PaymentDetail

class PaymentDetailAdapter: RecyclerView.Adapter<PaymentDetailAdapter.PaymentDetailViewHolder>() {

    private val payments = mutableListOf<PaymentDetail>()

    fun submitList(list: List<PaymentDetail>) {
        this.payments.clear()
        Log.d(TAG, "결제 내역 : $list")
        payments.addAll(list)
        notifyDataSetChanged()
    }

    class PaymentDetailViewHolder(
        private val binding: ItemPaymentPlusListBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PaymentDetail){
            binding.data = data
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentDetailViewHolder {
        val binding = ItemPaymentPlusListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PaymentDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentDetailViewHolder, position: Int) {

        holder.onBind(payments[position])
    }

    override fun getItemCount(): Int {
        return payments.size
    }

    companion object {
        const val TAG = "PaymentDetailAdapter"
    }
}
