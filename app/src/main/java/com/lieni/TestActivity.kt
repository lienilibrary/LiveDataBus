package com.lieni

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lieni.livedatabus.LiveEventBus
import kotlinx.android.synthetic.main.activity_main.*

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iv_btn.setOnClickListener {
            LiveEventBus.get().with("target_1").value = "new"
        }
    }
}