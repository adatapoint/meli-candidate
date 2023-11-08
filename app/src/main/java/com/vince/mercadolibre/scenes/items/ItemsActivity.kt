package com.vince.mercadolibre.scenes.items

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vince.mercadolibre.R
import com.vince.mercadolibre.databinding.ActivityItemsBinding
import com.vince.mercadolibre.scenes.detaileditem.DetailedItemActivity
import com.vince.mercadolibre.scenes.ui.components.TopBar
import com.vince.mercadolibre.utils.ConstantsHelper.ARG_ITEM_ID
import com.vince.mercadolibre.utils.ConstantsHelper.ARG_QUERY
import com.vince.mercadolibre.utils.ConstantsHelper.EMPTY
import com.vince.mercadolibre.utils.launchActivity
import com.vince.mercadolibre.utils.viewBinding

class ItemsActivity : AppCompatActivity(), ItemsFragment.OnItemClickListener {

    private val binding by viewBinding(ActivityItemsBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setViews()
        setQueryItemsFragment()
    }

    private fun setQueryItemsFragment() {
        val query = intent.getStringExtra(ARG_QUERY) ?: EMPTY
        if (supportFragmentManager.findFragmentById(R.id.flQueryItems) == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.flQueryItems, ItemsFragment.newInstanceByQuery(query))
                .commit()
        }
    }

    private fun setViews() {
        binding.cvTopBar.setContent {
            TopBar(onBackClick = { finish() })
        }
    }

    override fun onItemClick(itemId: String) {
        launchActivity<DetailedItemActivity> {
            putExtra(ARG_ITEM_ID, itemId)
        }
    }
}