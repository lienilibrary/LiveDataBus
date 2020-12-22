package com.lieni

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.lieni.livedatabus.LiveEventBus
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LiveEventBus.get().with("target_1", String::class.java)
            .observe(this) {
                Log.i("MainActivity", it)
            }

        image_view.setOnClickListener {

        }

        iv_btn.setOnClickListener {
            startActivity(Intent(this, TestActivity::class.java))
        }
    }
}