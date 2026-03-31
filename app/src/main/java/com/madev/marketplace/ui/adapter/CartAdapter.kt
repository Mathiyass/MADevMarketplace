package com.madev.marketplace.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madev.marketplace.domain.model.Product
import com.madev.marketplace.databinding.ItemCartProductBinding

class CartAdapter(
    private val onRemove: (Product) -> Unit
) : ListAdapter<Product, CartAdapter.VH>(CartDiff) {

    inner class VH(val b: ItemCartProductBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemCartProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val p = getItem(position)
        holder.b.tvCartTitle.text = p.title
        holder.b.tvCartPrice.text = p.formattedPrice
        holder.b.btnRemove.setOnClickListener { onRemove(p) }
    }

    companion object {
        val CartDiff = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(a: Product, b: Product) = a.productId == b.productId
            override fun areContentsTheSame(a: Product, b: Product) = a == b
        }
    }
}
