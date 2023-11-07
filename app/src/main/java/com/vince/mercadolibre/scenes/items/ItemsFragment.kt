package com.vince.mercadolibre.scenes.items

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vince.mercadolibre.R
import com.vince.mercadolibre.data.CallResult
import com.vince.mercadolibre.databinding.FragmentItemsBinding
import com.vince.mercadolibre.utils.ConstantsHelper.ARG_CATEGORY_ID
import com.vince.mercadolibre.utils.ConstantsHelper.ARG_QUERY
import com.vince.mercadolibre.utils.ConstantsHelper.ARG_RECYCLERVIEW_LAYOUT
import com.vince.mercadolibre.utils.ConstantsHelper.DEFAULT_CATEGORY
import com.vince.mercadolibre.utils.ConstantsHelper.EMPTY
import com.vince.mercadolibre.utils.ConstantsHelper.LOG_TAG
import com.vince.mercadolibre.utils.shouldImplement
import com.vince.mercadolibre.utils.showToast
import com.vince.mercadolibre.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemsFragment : Fragment(R.layout.fragment_items), ItemListener {

    private val itemsViewModel: ItemsViewModel by viewModel()
    private val binding by viewBinding(FragmentItemsBinding::bind)
    private var listener: OnItemClickListener? = null
    private var savedRecyclerLayoutState: Parcelable? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context.shouldImplement(OnItemClickListener::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView(savedInstanceState)

        if (requireArguments().containsKey(ARG_QUERY)) {
            getItemsByQuery(requireArguments().getString(ARG_QUERY) ?: EMPTY)
        } else {
            getItemsByCategory(requireArguments().getString(ARG_CATEGORY_ID) ?: DEFAULT_CATEGORY)
        }

        savedRecyclerLayoutState?.let {
            binding.rvItems.layoutManager?.onRestoreInstanceState(it)
        }
    }

    override fun onResume() {
        super.onResume()
        // Restaura la posición del RecyclerView si está guardada
        if (savedRecyclerLayoutState != null) {
            binding.rvItems.layoutManager?.onRestoreInstanceState(savedRecyclerLayoutState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Guarda la posición actual del RecyclerView
        savedRecyclerLayoutState = binding.rvItems.layoutManager?.onSaveInstanceState()
        outState.putParcelable(ARG_RECYCLERVIEW_LAYOUT, savedRecyclerLayoutState)
    }

    private fun setRecyclerView(savedInstanceState: Bundle?) {
        binding.rvItems.apply {
            adapter = ItemAdapter(this@ItemsFragment)
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
        if (savedInstanceState != null) {
            savedRecyclerLayoutState = savedInstanceState.getBundle("recycler_layout")
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
                    Log.d(LOG_TAG, "$result")
                    (binding.rvItems.adapter as ItemAdapter).setFirstPageElements(result.data.items)
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

    override fun onItemClick(itemId: String) {
        listener?.onItemClick(itemId)
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
        fun onItemClick(itemId: String)
    }
}
