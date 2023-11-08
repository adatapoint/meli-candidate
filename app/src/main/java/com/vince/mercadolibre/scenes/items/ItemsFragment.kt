package com.vince.mercadolibre.scenes.items

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vince.mercadolibre.R
import com.vince.mercadolibre.data.CallResult
import com.vince.mercadolibre.databinding.FragmentItemsBinding
import com.vince.mercadolibre.domain.models.Item
import com.vince.mercadolibre.utils.ConstantsHelper
import com.vince.mercadolibre.utils.ConstantsHelper.ARG_CATEGORY_ID
import com.vince.mercadolibre.utils.ConstantsHelper.ARG_QUERY
import com.vince.mercadolibre.utils.ConstantsHelper.LOG_TAG
import com.vince.mercadolibre.utils.InternetDelegate
import com.vince.mercadolibre.utils.setAsGone
import com.vince.mercadolibre.utils.setAsVisible
import com.vince.mercadolibre.utils.setSafeOnClickListener
import com.vince.mercadolibre.utils.shouldImplement
import com.vince.mercadolibre.utils.showToast
import com.vince.mercadolibre.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemsFragment : Fragment(R.layout.fragment_items), ItemListener {

    private val itemsViewModel: ItemsViewModel by viewModel()
    private val binding by viewBinding(FragmentItemsBinding::bind)
    private lateinit var internetDelegate: InternetDelegate
    private var listener: OnItemClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context.shouldImplement(OnItemClickListener::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        internetDelegate = InternetDelegate(requireContext())
        setViews()
        setRecyclerView()
        getItems()
    }

    private fun getItems() {
        if (internetDelegate.hasInternet()) {
            binding.llNoNetwork.setAsGone()
            if (requireArguments().containsKey(ARG_QUERY)) {
                val query = requireArguments().getString(ARG_QUERY) ?: ConstantsHelper.EMPTY
                getItemsByQuery(query)
                setQueryTitle(query)
            } else {
                getItemsByCategory(requireArguments().getString(ARG_CATEGORY_ID) ?: ConstantsHelper.DEFAULT_CATEGORY)
                setCategoryTitle()
            }
        } else {
            binding.llNoNetwork.setAsVisible()
        }
    }

    private fun setViews() {
        binding.btnTryAgain.setSafeOnClickListener {
            getItems()
        }
    }

    private fun setCategoryTitle() {
        binding.run {
            tvTitleQuery.text = getString(R.string.interesting_items)
        }
    }

    private fun setQueryTitle(query: String) {
        binding.run {
            tvTitleQuery.text =  String.format(getString(R.string.search_results), query)
        }
    }

    private fun setRecyclerView() {
        binding.rvItems.apply {
            adapter = ItemAdapter(this@ItemsFragment)
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
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
                    setItemsInAdapter(result.data.items)
                }
            }
        }
    }

    private fun setItemsInAdapter(items: List<Item>) {
        (binding.rvItems.adapter as ItemAdapter).setFirstPageElements(items)
        if (items.isEmpty()) {
            binding.tvEmptyState.setAsVisible()
        }
    }

    private fun getItemsByQuery(query: String) {
        itemsViewModel.getItemsByQuery(query).observe(this) { result ->
            when (result) {
                is CallResult.Failure -> showToast(R.string.no_items_error)
                is CallResult.Loading -> {
                    // TODO
                    // this state allows me to show that
                    // the information is being retrieved
                }
                is CallResult.Success -> {
                    Log.d(LOG_TAG, "$result")
                    setItemsInAdapter(result.data.items)
                }
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
