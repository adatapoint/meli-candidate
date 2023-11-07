package com.vince.mercadolibre.scenes.detaileditem

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import com.vince.mercadolibre.R
import com.vince.mercadolibre.data.CallResult
import com.vince.mercadolibre.domain.models.DetailedItem
import com.vince.mercadolibre.domain.models.Picture
import com.vince.mercadolibre.scenes.ui.components.ImageCarousel
import com.vince.mercadolibre.scenes.ui.components.TopBar
import com.vince.mercadolibre.utils.ConstantsHelper.EMPTY
import com.vince.mercadolibre.utils.ConstantsHelper.NO_VALUE
import org.koin.compose.koinInject

@Composable
fun DetailedItemScreen(
    itemId: String,
    onBackAction: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val viewModel: DetailedItemViewModel = koinInject()
    var detailedItem by remember {
        mutableStateOf(
            DetailedItem(EMPTY, EMPTY, listOf(), EMPTY, NO_VALUE, listOf(), EMPTY)
        )
    }
    LaunchedEffect(Unit) {
        viewModel.getDetailedItem(itemId).observe(lifecycleOwner) { result ->
            when (result) {
                is CallResult.Failure -> {}
                is CallResult.Loading -> {}
                is CallResult.Success -> detailedItem = result.data
            }
        }
    }

    Scaffold { paddingValues ->

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(color = Color(R.color.meli_yellow))
                .padding(paddingValues)
                .padding(8.dp)
                .padding(bottom = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TopBar { onBackAction.invoke() }
            Box(
                modifier = Modifier
                    .clip(RectangleShape)
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxSize()
                    .aspectRatio(1f)
            ) {
                ImageCarousel(pictures = detailedItem.pictures)
                Log.d("asdf", "${detailedItem.pictures}")
            }
            Text(text = detailedItem.title)
            detailedItem.attributes.forEach { attribute ->
                Text(text = "${attribute.name}: ${attribute.value}")
            }
            Text(text = detailedItem.condition)
        }
    }
}
