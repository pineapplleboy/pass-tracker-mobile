package com.example.passtracker.ui.component

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.passtracker.R
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun BottomSheet(passes: List<String>, modifier: Modifier = Modifier) {
    val offsetY = remember { Animatable(0f) }
    val screenHeightPx = LocalDensity.current.run { LocalConfiguration.current.screenHeightDp.dp.toPx() }
    val maxOffset = screenHeightPx * 0.5f
    val scope = rememberCoroutineScope()
    var isFullyExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        offsetY.snapTo( maxOffset )
    }

    LaunchedEffect(offsetY.value) {
        isFullyExpanded = offsetY.value <= maxOffset / 4
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .offset { IntOffset(0, offsetY.value.roundToInt()) }
            .background(
                color = colorResource(R.color.bottom_background),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            )
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {},
                    onDragEnd = {
                        val targetOffset = if (offsetY.value > maxOffset / 2) maxOffset else 0f
                        scope.launch {
                            offsetY.animateTo(targetOffset, animationSpec = tween(durationMillis = 500))
                        }
                    },
                    onDragCancel = {}
                ) { change, dragAmount ->
                    val dragDelta = dragAmount.y
                    val newOffset = (offsetY.value + dragDelta).coerceIn(0f, maxOffset)
                    if (newOffset != offsetY.value) {
                        scope.launch {
                            offsetY.snapTo(newOffset)
                        }
                    }
                    change.consume()
                }
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(R.drawable.rectangle_40),
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Text(
            text = "Последние добавленные",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.size(16.dp))
        LazyColumn(
            modifier = if (isFullyExpanded) {
                Modifier.weight(1f)
            } else {
                Modifier.height((screenHeightPx - offsetY.value).toDp())
            }
        ) {
            items(passes) {
                PassesCard(modifier = Modifier.padding(horizontal = 16.dp))
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }

}

@Composable
fun Float.toDp() = with(LocalDensity.current) { this@toDp.toDp() }


@Preview
@Composable
fun BottomSheetPreview() {
    BottomSheet(listOf("fgd", "gfdgdf", "fgdf"))
}