package com.example.passtracker.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.passtracker.R
import com.example.passtracker.app.ui.screen.parseAndFormatIsoDate
import com.example.passtracker.domain.model.RequestShort

@Composable
fun PassesCard(modifier: Modifier = Modifier, pass: RequestShort,
               onItemSelected: (String) -> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(32.dp))
            .clickable { onItemSelected(pass.id.toString()) }
    ) {
        Row(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = pass.typeRequest.toString(),
                style = MaterialTheme.typography.labelLarge
            )
            Image(
                painter = painterResource(R.drawable.circle),
                contentDescription = null
            )
        }
        Row(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
                DataTextField(date = parseAndFormatIsoDate(pass.startDate))
                DataTextField(startDate = false, date = parseAndFormatIsoDate(pass.finishDate))
        }
    }
}

//@Preview
//@Composable
//fun PassesCardPreview() {
//    PassesCard()
//}
