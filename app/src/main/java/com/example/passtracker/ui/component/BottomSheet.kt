package com.example.passtracker.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(passes: List<String>, modifier: Modifier = Modifier) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
        confirmValueChange = { newState ->
            newState != SheetValue.Hidden
        })
    ModalBottomSheet(
        onDismissRequest = {},
        sheetState = sheetState,
        scrimColor = Color.Transparent,
        modifier = modifier
            .fillMaxHeight(0.85f)
    ) {
        Text(
            text = "Последние добавленные",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(horizontal = 16.dp)

        )
        Spacer(modifier.size(16.dp))
        LazyColumn {
            items(passes) {
                PassesCard(modifier = Modifier.padding(horizontal = 16.dp))
                Spacer(modifier.size(16.dp))
            }
        }
    }

}



@Preview
@Composable
fun BottomSheetPreview() {
    BottomSheet(listOf("fgd", "gfdgdf", "fgdf"))
}