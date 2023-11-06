package com.vince.mercadolibre.scenes.items

import androidx.recyclerview.widget.RecyclerView
import com.vince.mercadolibre.databinding.ElementItemBinding
import com.vince.mercadolibre.domain.models.Item
import com.vince.mercadolibre.utils.getFormattedMoneyWithCurrency
import com.vince.mercadolibre.utils.loadImage
import com.vince.mercadolibre.utils.setSafeOnClickListener

class ItemViewHolder(private val binding: ElementItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item, listener: ItemListener) {
        with(binding) {
            tvTitle.text = item.title
            tvPrice.text = getFormattedMoneyWithCurrency(item.price, item.currency)

            clItem.setSafeOnClickListener { listener.onClick() }
            ivItemPicture.loadImage(item.image)
        }
    }
}
