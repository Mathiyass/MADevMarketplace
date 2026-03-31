package com.madev.marketplace.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.madev.marketplace.R
import com.madev.marketplace.databinding.FragmentSearchBinding
import com.madev.marketplace.ui.adapter.ProductsAdapter
import com.madev.marketplace.ui.search.SearchViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val vm: SearchViewModel by viewModels()
    private lateinit var adapter: ProductsAdapter
    private var searchJob: Job? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
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
        binding.rvSearchResults.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSearchResults.adapter = adapter

        // Debounced search
        binding.etSearch.doAfterTextChanged { text ->
            searchJob?.cancel()
            val query = text?.toString().orEmpty().trim()
            if (query.isEmpty()) {
                binding.tvSearchHint.visibility = View.VISIBLE
                adapter.submitList(emptyList())
                return@doAfterTextChanged
            }
            
            searchJob = lifecycleScope.launch {
                delay(300)
                binding.tvSearchHint.visibility = View.GONE
                vm.search(query)
            }
        }

        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                vm.search(binding.etSearch.text?.toString().orEmpty().trim())
                true
            } else false
        }

        lifecycleScope.launch {
            vm.results.collect { 
                adapter.submitList(it)
                binding.tvSearchHint.visibility = if (it.isEmpty() && binding.etSearch.text.isNullOrBlank()) View.VISIBLE else View.GONE
                binding.tvEmpty.visibility = if (it.isEmpty() && !binding.etSearch.text.isNullOrBlank()) View.VISIBLE else View.GONE
            }
        }

        lifecycleScope.launch {
            vm.isLoading.collect { loading ->
                binding.loading.visibility = if (loading) View.VISIBLE else View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
