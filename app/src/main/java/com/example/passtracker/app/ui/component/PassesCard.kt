package com.example.passtracker.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passtracker.R
import com.example.passtracker.app.ui.screen.parseAndFormatIsoDate
import com.example.passtracker.domain.model.RequestShort
import com.example.passtracker.domain.model.StatusRequest
import com.example.passtracker.domain.model.TypeRequest

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
                text = when(pass.typeRequest) {
                    TypeRequest.EducationalActivity -> "Учебная деятельность"
                    TypeRequest.Disease -> "Болезнь"
                    TypeRequest.FamilyCircumstances-> "Семейные обстоятельства"
                },
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.weight(1f)
            )
            Column (modifier = Modifier.wrapContentHeight()){
                Image(
                    painter = painterResource(R.drawable.circle),
                    contentDescription = null,
                    colorFilter = when(pass.statusRequest) {
                        StatusRequest.Declined -> ColorFilter.tint(colorResource(R.color.red))
                        StatusRequest.Pending -> ColorFilter.tint(colorResource(R.color.yellow))
                        StatusRequest.Accepted -> ColorFilter.tint(colorResource(R.color.green))
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    text = when(pass.statusRequest) {
                        StatusRequest.Declined -> "Отоклонен"
                        StatusRequest.Pending -> "В ожидании"
                        StatusRequest.Accepted -> "Принят"
                    },
                    style = MaterialTheme.typography.labelSmall,
                    color = colorResource(R.color.gray),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

        }
        Row(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
                DataTextField(modifier = Modifier.weight(1f),date = parseAndFormatIsoDate(pass.startDate))
            Spacer(modifier = Modifier.size(40.dp))
                DataTextField(modifier = Modifier.weight(1f),startDate = false, date = parseAndFormatIsoDate(pass.finishDate))
        }
    }
}

//@Preview
//@Composable
//fun PassesCardPreview() {
//    PassesCard()
//}
