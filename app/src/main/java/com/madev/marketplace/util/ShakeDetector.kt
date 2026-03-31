package com.madev.marketplace.util

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlin.math.sqrt

class ShakeDetector(private val onShake: () -> Unit) : SensorEventListener {
    private var lastShakeTimestamp: Long = 0
    private var shakeThreshold = 2.7f // G-force threshold from MD

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            val x = event.values[0] / SensorManager.GRAVITY_EARTH
            val y = event.values[1] / SensorManager.GRAVITY_EARTH
            val z = event.values[2] / SensorManager.GRAVITY_EARTH

            val gForce = sqrt((x * x + y * y + z * z).toDouble()).toFloat()

            if (gForce > shakeThreshold) {
                val now = System.currentTimeMillis()
                if (lastShakeTimestamp + SHAKE_SLOP_TIME_MS > now) return
                
                lastShakeTimestamp = now
                onShake()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    companion object {
        private const val SHAKE_SLOP_TIME_MS = 1000
    }
}
