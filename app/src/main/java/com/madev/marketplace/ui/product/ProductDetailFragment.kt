package com.madev.marketplace.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.madev.marketplace.R
import com.madev.marketplace.domain.model.ProductCategory
import com.madev.marketplace.databinding.FragmentProductDetailBinding
import com.madev.marketplace.ui.product.ProductDetailViewModel
import io.noties.markwon.Markwon
import kotlinx.coroutines.launch

class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private val vm: ProductDetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productId = arguments?.getString("productId") ?: return
        val markwon = Markwon.create(requireContext())

        vm.loadProduct(productId)

        lifecycleScope.launch {
            vm.product.collect { product ->
                product ?: return@collect
                binding.tvTitle.text = product.title
                binding.tvVersion.text = "v${product.version}"
                binding.tvSize.text = product.formattedSize
                binding.tvDownloads.text = "${product.downloadCount} downloads"
                binding.tvPrice.text = product.formattedPrice

                markwon.setMarkdown(binding.tvDescription, product.description)

                product.previewCode?.let {
                    binding.cardPreview.visibility = View.VISIBLE
                    binding.tvPreviewCode.text = it
                }
                product.requirements?.let {
                    binding.cardRequirements.visibility = View.VISIBLE
                    binding.tvRequirements.text = it
                }

                val (colorRes, label) = when (product.category) {
                    ProductCategory.Local_AI  -> R.color.cat_local_ai to "LOCAL AI"
                    ProductCategory.Scripts   -> R.color.cat_scripts to "SCRIPTS"
                    ProductCategory.Linux_ISO -> R.color.cat_linux_iso to "LINUX ISO"
                    ProductCategory.Tools     -> R.color.cat_tools to "TOOLS"
                }
                val color = ContextCompat.getColor(requireContext(), colorRes)
                binding.categoryBar.setBackgroundColor(color)
                binding.chipCategory.text = label
            }
        }

        lifecycleScope.launch {
            vm.hasPurchased.collect { purchased ->
                binding.btnPurchase.visibility = if (purchased) View.GONE else View.VISIBLE
                binding.btnDownload.visibility = if (purchased) View.VISIBLE else View.GONE
            }
        }

        lifecycleScope.launch {
            vm.signedUrl.collect { signedUrl ->
                signedUrl ?: return@collect
                // In a real app, you'd trigger a background download service
                Snackbar.make(binding.root, "DOWNLOAD_STARTED:: ${signedUrl.filename}", Snackbar.LENGTH_SHORT).show()
            }
        }

        lifecycleScope.launch {
            vm.error.collect { err ->
                if (err != null) {
                    Snackbar.make(binding.root, "ERROR:: $err", Snackbar.LENGTH_LONG).show()
                }
            }
        }

        binding.btnPurchase.setOnClickListener {
            val product = vm.product.value ?: return@setOnClickListener
            vm.createOrder(productId, product.price) { order ->
                launchPayHere(productId, product.price, order.orderId)
            }
        }

        binding.btnDownload.setOnClickListener {
            vm.getSignedUrl(productId)
        }
    }

    private fun launchPayHere(productId: String, amount: Double, orderId: String) {
        // Placeholder for real PayHere SDK integration
        // Requires full merchant_secret and proper initialization
        Toast.makeText(requireContext(), "INITIATING_PAYMENT_GATEWAY::", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
