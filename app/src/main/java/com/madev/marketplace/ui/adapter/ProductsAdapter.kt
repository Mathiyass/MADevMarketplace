package com.madev.marketplace.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madev.marketplace.R
import com.madev.marketplace.domain.model.Product
import com.madev.marketplace.domain.model.ProductCategory
import com.madev.marketplace.databinding.ItemProductBinding

class ProductsAdapter(
    private val onClick: (Product) -> Unit
) : ListAdapter<Product, ProductsAdapter.VH>(ProductDiff) {

    inner class VH(private val b: ItemProductBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(p: Product) {
            b.tvTitle.text = p.title
            b.tvDescription.text = p.description.lines().firstOrNull()?.removePrefix("## ")?.trim() ?: ""
            b.tvPrice.text = p.formattedPrice
            b.tvVersion.text = p.version
            b.tvSize.text = p.formattedSize

            val (colorRes, label) = when (p.category) {
                ProductCategory.Local_AI  -> R.color.cat_local_ai to "LOCAL AI"
                ProductCategory.Scripts   -> R.color.cat_scripts to "SCRIPTS"
                ProductCategory.Linux_ISO -> R.color.cat_linux_iso to "LINUX ISO"
                ProductCategory.Tools     -> R.color.cat_tools to "TOOLS"
            }
            val color = ContextCompat.getColor(b.root.context, colorRes)
            b.categoryBar.setBackgroundColor(color)
            b.chipCategory.text = label
            b.chipCategory.setTextColor(color)

            b.root.setOnClickListener { onClick(p) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    companion object {
        val ProductDiff = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(a: Product, b: Product) = a.productId == b.productId
            override fun areContentsTheSame(a: Product, b: Product) = a == b
        }
    }
}
