package com.vince.mercadolibre.scenes.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.vince.mercadolibre.domain.models.Picture
import com.vince.mercadolibre.utils.ConstantsHelper.EMPTY
import com.vince.mercadolibre.utils.ConstantsHelper.SLASH

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageCarousel(
    modifier: Modifier = Modifier,
    pictures: List<Picture>,
) {
    val state = rememberPagerState()
    val imageUrl = remember { mutableStateOf(EMPTY) }
    HorizontalPager(
        state = state,
        count = pictures.size,
        modifier = modifier
            .height(370.dp)
            .fillMaxWidth(),
    ) { page ->
        imageUrl.value = pictures[page].picture
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            contentAlignment = Alignment.BottomCenter,
        ) {
            AsyncImage(
                modifier = modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(10.dp)
                    ),
                contentScale = ContentScale.Fit,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl.value)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
            )
            Box(
                modifier = Modifier
                    .padding(end = 12.dp, bottom = 12.dp)
                    .height(35.dp)
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = Color.Black.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
                    .align(Alignment.BottomEnd)
                    .padding(vertical = 6.dp, horizontal = 12.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "${page + 1}$SLASH${pictures.size}",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.White,
                    ),
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun ImageCarouselPreview() {
    val media = listOf(
        Picture(""),
        Picture(""),
        Picture(""),
        Picture("")
    )
    ImageCarousel(pictures = media)
}
