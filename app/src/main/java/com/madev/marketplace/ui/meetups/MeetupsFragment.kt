package com.madev.marketplace.ui.meetups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.madev.marketplace.databinding.FragmentMeetupsBinding
import com.madev.marketplace.ui.adapter.MeetupsAdapter
import kotlinx.coroutines.launch

class MeetupsFragment : Fragment() {

    private var _binding: FragmentMeetupsBinding? = null
    private val binding get() = _binding!!
    private val vm: MeetupsViewModel by viewModels()
    private lateinit var adapter: MeetupsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMeetupsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        adapter = MeetupsAdapter()
        binding.rvMeetups.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMeetups.adapter = adapter

        lifecycleScope.launch {
            vm.meetups.collect { 
                adapter.submitList(it)
                binding.tvEmpty.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
