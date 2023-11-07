package com.vince.mercadolibre.scenes.items

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vince.mercadolibre.R
import com.vince.mercadolibre.databinding.ActivityItemsBinding
import com.vince.mercadolibre.scenes.ui.components.TopBar
import com.vince.mercadolibre.utils.ConstantsHelper.DEFAULT_CATEGORY
import com.vince.mercadolibre.utils.viewBinding

class ItemsActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityItemsBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setViews()
        setQueryItemsFragment()
    }

    private fun setQueryItemsFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.flQueryItems, ItemsFragment.newInstanceByCategory(DEFAULT_CATEGORY))
            .commit()
    }

    private fun setViews() {
        binding.cvTopBar.setContent {
            TopBar(onBackClick = { finish() })
        }
    }
}