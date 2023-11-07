package com.vince.mercadolibre.scenes

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.vince.mercadolibre.R
import com.vince.mercadolibre.data.CallResult
import com.vince.mercadolibre.databinding.ActivityMainBinding
import com.vince.mercadolibre.scenes.detaileditem.DetailedItemActivity
import com.vince.mercadolibre.utils.ConstantsHelper.ARG_ITEM_ID
import com.vince.mercadolibre.utils.ConstantsHelper.DEFAULT_CATEGORY
import com.vince.mercadolibre.utils.launchActivity
import com.vince.mercadolibre.utils.showToast
import com.vince.mercadolibre.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        mainViewModel.getItems("").observe(this) {
//            Log.d("asdf", "$it")
//        }

//        mainViewModel.getSuggestionsQueries("ojos").observe(this) {
//            Log.d("asdf", "$it")
//        }

        mainViewModel.getItemsByCategory(DEFAULT_CATEGORY).observe(this) { result ->
            when (result) {
                is CallResult.Failure -> showToast(R.string.no_items_error)
                is CallResult.Success -> {

                    Log.d("asdf", "$result")
                }
                is CallResult.Loading -> {
                    // TODO
                    // this state allows me to show that
                    // the information is being retrieved
                }
            }
        }

        launchActivity<DetailedItemActivity> {
            putExtra(ARG_ITEM_ID, "MCO1624836818")
        }
    }
}
