package com.example.laba5

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BroadcastsActivity : AppCompatActivity() {

    private lateinit var powerReceiver: BroadcastReceiver
    private lateinit var airplaneReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcasts)
    }

    override fun onResume() {
        super.onResume()

        // При подключении питания
        powerReceiver = object : BroadcastReceiver() {
            override fun onReceive(ctx: Context, intent: Intent) {
                Toast.makeText(ctx, "Підключено живлення", Toast.LENGTH_SHORT).show()
            }
        }
        registerReceiver(powerReceiver, IntentFilter(Intent.ACTION_POWER_CONNECTED))

        // Смена авиарежима
        airplaneReceiver = object : BroadcastReceiver() {
            override fun onReceive(ctx: Context, intent: Intent) {
                val isAirplaneOn = intent.getBooleanExtra("state", false)
                Toast.makeText(
                    ctx,
                    "Авіарежим: ${ if (isAirplaneOn) "Увімкнено" else "Вимкнено" }",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        registerReceiver(
            airplaneReceiver,
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        )
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(powerReceiver)
        unregisterReceiver(airplaneReceiver)
    }
}
