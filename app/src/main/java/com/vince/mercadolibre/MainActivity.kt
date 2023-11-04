package com.vince.mercadolibre

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vince.mercadolibre.databinding.ActivityMainBinding
import com.vince.mercadolibre.utils.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}