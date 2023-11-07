package com.vince.mercadolibre.scenes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vince.mercadolibre.R
import com.vince.mercadolibre.databinding.ActivityMainBinding
import com.vince.mercadolibre.scenes.detaileditem.DetailedItemActivity
import com.vince.mercadolibre.scenes.items.ItemsActivity
import com.vince.mercadolibre.scenes.items.ItemsFragment
import com.vince.mercadolibre.scenes.ui.components.SearchBar
import com.vince.mercadolibre.utils.ConstantsHelper.ARG_ITEM_ID
import com.vince.mercadolibre.utils.ConstantsHelper.ARG_QUERY
import com.vince.mercadolibre.utils.ConstantsHelper.DEFAULT_CATEGORY
import com.vince.mercadolibre.utils.launchActivity
import com.vince.mercadolibre.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), ItemsFragment.OnItemClickListener {

    private val mainViewModel: MainViewModel by viewModel()
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setViews()
        setCategoryItemsFragment()
    }

    private fun setViews() {
        binding.cvSearchBar.setContent {
            SearchBar(onSearch = { query ->
                launchActivity<ItemsActivity> {
                    putExtra(ARG_QUERY, query)
                }
            })
        }
    }

    private fun setCategoryItemsFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.flCategoryItems, ItemsFragment.newInstanceByCategory(DEFAULT_CATEGORY))
            .commit()
    }

    override fun onItemClick(itemId: Int) {
        launchActivity<DetailedItemActivity> {
            putExtra(ARG_ITEM_ID, itemId)
        }
    }
}
