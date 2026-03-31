package com.madev.marketplace.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.madev.marketplace.R
import com.madev.marketplace.domain.model.ProductCategory
import com.madev.marketplace.databinding.FragmentHomeBinding
import com.madev.marketplace.ui.adapter.ProductsAdapter
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val vm: HomeViewModel by viewModels()
    private lateinit var adapter: ProductsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ProductsAdapter { product ->
            val bundle = Bundle().apply {
                putString("productId", product.productId)
            }
            findNavController().navigate(R.id.productDetailFragment, bundle)
        }

        binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProducts.adapter = adapter

        lifecycleScope.launch {
            vm.products.collect { 
                adapter.submitList(it)
                binding.tvEmpty.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            }
        }

        lifecycleScope.launch {
            vm.isLoading.collect { 
                binding.loading.visibility = if (it) View.VISIBLE else View.GONE
            }
        }

        binding.chipGroupCategories.setOnCheckedStateChangeListener { _, checkedIds ->
            val category = when (checkedIds.firstOrNull()) {
                R.id.chip_localai -> ProductCategory.Local_AI
                R.id.chip_scripts -> ProductCategory.Scripts
                R.id.chip_linux   -> ProductCategory.Linux_ISO
                else               -> null
            }
            vm.loadProducts(category)
        }

        binding.swipeRefresh.setOnRefreshListener {
            vm.refresh()
            binding.swipeRefresh.isRefreshing = false
        }
        binding.swipeRefresh.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.teal_accent))

        binding.btnCart.setOnClickListener {
            findNavController().navigate(R.id.cartFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
