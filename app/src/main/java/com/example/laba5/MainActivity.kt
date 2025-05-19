package com.example.laba5

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.floor
import kotlin.math.max
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val N = 88
        val items = List(N) {
            CellItem(
                value = Random.nextInt(1, 100),
                color = Color.argb(
                    255,
                    Random.nextInt(256),
                    Random.nextInt(256),
                    Random.nextInt(256)
                )
            )
        }


        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)


        val displayMetrics = resources.displayMetrics
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        val spanCount = max(1, floor(screenWidthDp / 96f).toInt())


        recyclerView.layoutManager = GridLayoutManager(this, spanCount)
        recyclerView.setHasFixedSize(true)


        recyclerView.adapter = CellAdapter(items) { item ->
            AlertDialog.Builder(this)
                .setTitle("Значение")
                .setMessage("Ви обрали число "+item.value.toString()+" 🗿")
                .setPositiveButton("ОК", null)
                .show()
        }

        findViewById<Button>(R.id.btnBroadcasts).setOnClickListener {
            startActivity(Intent(this, BroadcastsActivity::class.java))
        }
    }



}
