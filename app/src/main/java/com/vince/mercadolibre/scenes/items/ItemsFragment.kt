package com.vince.mercadolibre.scenes.items

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.vince.mercadolibre.R
import com.vince.mercadolibre.data.CallResult
import com.vince.mercadolibre.databinding.FragmentItemsBinding
import com.vince.mercadolibre.utils.ConstantsHelper
import com.vince.mercadolibre.utils.ConstantsHelper.ARG_CATEGORY_ID
import com.vince.mercadolibre.utils.ConstantsHelper.ARG_QUERY
import com.vince.mercadolibre.utils.ConstantsHelper.DEFAULT_CATEGORY
import com.vince.mercadolibre.utils.ConstantsHelper.EMPTY
import com.vince.mercadolibre.utils.shouldImplement
import com.vince.mercadolibre.utils.showToast
import com.vince.mercadolibre.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemsFragment : Fragment(R.layout.fragment_items) {

    private val itemsViewModel: ItemsViewModel by viewModel()
    private val binding by viewBinding(FragmentItemsBinding::bind)
    private var listener: OnItemClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context.shouldImplement(OnItemClickListener::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (requireArguments().containsKey(ARG_QUERY)) {
            getItemsByQuery(requireArguments().getString(ARG_QUERY) ?: EMPTY)
        } else {
            getItemsByCategory(requireArguments().getString(ARG_CATEGORY_ID) ?: DEFAULT_CATEGORY)
        }
    }

    private fun getItemsByCategory(categoryId: String) {
        itemsViewModel.getItemsByCategory(categoryId).observe(this) { result ->
            when (result) {
                is CallResult.Failure -> showToast(R.string.no_items_error)
                is CallResult.Loading -> {
                    // TODO
                    // this state allows me to show that
                    // the information is being retrieved
                }
                is CallResult.Success -> {
                    Log.d(ConstantsHelper.LOG_TAG, "$result")

                }
            }
        }
    }

    private fun getItemsByQuery(query: String) {
        itemsViewModel.getItemsByQuery(query).observe(this) { result ->
            when (result) {
                is CallResult.Failure -> {}
                is CallResult.Loading -> {}
                is CallResult.Success -> {}
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {

        fun newInstanceByCategory(categoryId: String) = ItemsFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_CATEGORY_ID, categoryId)
            }
        }

        fun newInstanceByQuery(query: String) = ItemsFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_QUERY, query)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(itemId: Int)
    }
}
