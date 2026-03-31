package com.madev.marketplace.ui.downloads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.madev.marketplace.databinding.FragmentDownloadsBinding
import com.madev.marketplace.data.repository.OrderRepository
import com.madev.marketplace.service.DownloadService
import com.madev.marketplace.ui.adapter.DownloadsAdapter
import com.madev.marketplace.ui.viewmodel.DownloadsViewModel
import kotlinx.coroutines.launch

class DownloadsFragment : Fragment() {

    private var _binding: FragmentDownloadsBinding? = null
    private val binding get() = _binding!!
    private val vm: DownloadsViewModel by viewModels()
    private lateinit var adapter: DownloadsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDownloadsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DownloadsAdapter { item ->
            lifecycleScope.launch {
                OrderRepository.getSignedDownloadUrl(item.productId)
                    .onSuccess { signed ->
                        DownloadService.start(requireContext(), signed.url, signed.filename)
                        Snackbar.make(binding.root, "Download started!", Snackbar.LENGTH_SHORT).show()
                    }
                    .onFailure { e ->
                        Snackbar.make(binding.root, e.message ?: "Download failed", Snackbar.LENGTH_LONG).show()
                    }
            }
        }
        binding.rvDownloads.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDownloads.adapter = adapter

        lifecycleScope.launch {
            vm.items.collect { items ->
                adapter.submitList(items)
                binding.emptyState.visibility = if (items.isEmpty()) View.VISIBLE else View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
