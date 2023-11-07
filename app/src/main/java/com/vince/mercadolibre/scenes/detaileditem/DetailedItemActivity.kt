package com.vince.mercadolibre.scenes.detaileditem

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vince.mercadolibre.R
import com.vince.mercadolibre.utils.ConstantsHelper.ARG_ITEM_ID
import com.vince.mercadolibre.utils.ConstantsHelper.EMPTY

class DetailedItemActivity : AppCompatActivity() {

    private val itemId by lazy { intent.getStringExtra(ARG_ITEM_ID) ?: EMPTY }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            if (itemId.isNotEmpty()) {
                DetailedItemScreen(
                    itemId = itemId,
                    onBackAction = { finish() }
                )
            } else {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = stringResource(id = R.string.item_no_available)
                    )
                }
            }
        }
    }
}
