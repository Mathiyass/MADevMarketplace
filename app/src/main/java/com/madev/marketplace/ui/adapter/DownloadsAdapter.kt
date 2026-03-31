package com.madev.marketplace.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madev.marketplace.domain.model.OrderItem
import com.madev.marketplace.databinding.ItemDownloadBinding

class DownloadsAdapter(
    private val onDownload: (OrderItem) -> Unit
) : ListAdapter<OrderItem, DownloadsAdapter.VH>(DownloadDiff) {

    inner class VH(private val b: ItemDownloadBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(item: OrderItem) {
            b.tvTitle.text = item.productId
            b.tvLicense.text = "LICENSE_KEY:: ${item.licenseKey.take(16)}..."
            b.chipStatus.text = "ACTIVE_ACCESS"
            b.btnDownload.setOnClickListener { onDownload(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemDownloadBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    companion object {
        val DownloadDiff = object : DiffUtil.ItemCallback<OrderItem>() {
            override fun areItemsTheSame(a: OrderItem, b: OrderItem) = a.itemId == b.itemId
            override fun areContentsTheSame(a: OrderItem, b: OrderItem) = a == b
        }
    }
}
