package com.vince.mercadolibre.scenes

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.vince.mercadolibre.databinding.ActivityMainBinding
import com.vince.mercadolibre.utils.ConstantsHelper.DEFAULT_CATEGORY
import com.vince.mercadolibre.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mainViewModel.getItems("").observe(this) {
            Log.d("asdf", "$it")
        }

        mainViewModel.getItemsByCategory(DEFAULT_CATEGORY).observe(this) {
            Log.d("asdf", "$it")
        }
    }
}
