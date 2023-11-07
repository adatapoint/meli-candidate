package com.vince.mercadolibre.scenes.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vince.mercadolibre.R

@Composable
fun TopBar(
    @DrawableRes backIcon: Int = R.drawable.ic_chevron_left,
    onBackClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .padding(vertical = 16.dp)
            .fillMaxWidth()
            .height(42.dp)
    ) {
        IconButton(onClick = { onBackClick.invoke() }) {
            Icon(
                painter = painterResource(id = backIcon),
                contentDescription = "backPress",
                tint = Color.Black
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun PreviewTopBar() {
    TopBar {}
}
