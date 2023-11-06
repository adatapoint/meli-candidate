package com.vince.mercadolibre.scenes.items

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vince.mercadolibre.databinding.ElementItemBinding
import com.vince.mercadolibre.domain.models.Item

class ItemAdapter(private val listener: ItemListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var elements = arrayListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            ElementItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )

    override fun getItemCount(): Int = elements.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).apply {
            setIsRecyclable(false)
            bind(elements[position], listener)
        }
    }

    fun hasItems(): Boolean = elements.size > 0


    fun setFirstPageElements(elements: List<Item>) {
        replaceAllItems(elements)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun replaceAllItems(newElements: List<Item>) {
        elements.clear()
        elements.addAll(newElements)
        notifyDataSetChanged()
    }
}
