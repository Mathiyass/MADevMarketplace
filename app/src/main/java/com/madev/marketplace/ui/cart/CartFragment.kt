package com.madev.marketplace.ui.cart

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.madev.marketplace.databinding.FragmentCartBinding
import com.madev.marketplace.ui.adapter.CartAdapter
import com.madev.marketplace.util.ShakeDetector
import kotlinx.coroutines.launch

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val vm: CartViewModel by viewModels()
    private lateinit var adapter: CartAdapter

    private var sensorManager: SensorManager? = null
    private var shakeDetector: ShakeDetector? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CartAdapter { product ->
            vm.removeItem(product.productId)
        }
        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCart.adapter = adapter

        lifecycleScope.launch {
            vm.state.collect { state ->
                when (state) {
                    is CartState.Loading -> {
                        binding.footerCard.visibility = View.GONE
                    }
                    is CartState.Success -> {
                        binding.footerCard.visibility = View.VISIBLE
                        adapter.submitList(state.items)
                        binding.tvTotalPrice.text = "LKR ${String.format("%.2f", state.total)}"
                    }
                    is CartState.Empty -> {
                        binding.footerCard.visibility = View.GONE
                        adapter.submitList(emptyList())
                        Toast.makeText(requireContext(), "CART_IS_EMPTY::", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        // Setup Shake Detector
        sensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        shakeDetector = ShakeDetector {
            if (vm.state.value is CartState.Success) {
                Toast.makeText(requireContext(), "PURGING_PENDING_ASSETS:: (SHAKE_DETECTED)", Toast.LENGTH_SHORT).show()
                vm.clearCart()
            }
        }

        binding.btnCheckout.setOnClickListener {
            Toast.makeText(requireContext(), "CHECKOUT_SEQUENCE_INITIATED::", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager?.registerListener(shakeDetector,
            sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_UI)
    }

    override fun onPause() {
        super.onPause()
        sensorManager?.unregisterListener(shakeDetector)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
