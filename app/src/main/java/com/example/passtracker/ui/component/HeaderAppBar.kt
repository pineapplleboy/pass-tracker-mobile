package com.example.passtracker.ui.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.passtracker.R

@Composable
fun HeaderAppBar(header: String, modifier: Modifier = Modifier, onProfileClicked: () -> Unit = {}) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = header,
            style = MaterialTheme.typography.titleLarge,
        )
        Icon(
            painter = painterResource(R.drawable.account_circle),
            contentDescription = null,
            modifier.clickable(onClick = {
                Log.d("BottomSheet", "Icon clicked")
                onProfileClicked()
            })
        )
    }
}

@Preview
@Composable
fun HeaderAppBarPreview() {
    HeaderAppBar(stringResource(R.string.my_passes))
}