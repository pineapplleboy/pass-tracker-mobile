package com.example.passtracker.app.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passtracker.R
import com.example.passtracker.domain.model.Profile
import com.example.passtracker.domain.model.Request
import com.example.passtracker.domain.model.RequestChange

@Composable
fun PassScreenContent(
    request: Request,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onDeletePass: () -> Unit = {},
    onEditPass: (RequestChange) -> Unit = {}
) {
    var startDate by remember { mutableStateOf(request.startDate) }
    var finishDate by remember { mutableStateOf(request.finishDate) }
    var typeRequest by remember { mutableStateOf(request.typeRequest) }
    var photo by remember { mutableStateOf(request.photo) }
    var editMode by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    painterResource(R.drawable.back_arrow_ic),
                    contentDescription = null,
                    tint = colorResource(R.color.black)
                )
            }
            Text(
                text = "Пропуск",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                color = colorResource(R.color.black),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            )
            IconButton(
                onClick = { editMode = !editMode },
            ) {
                Icon(
                    painter = if (editMode) painterResource(R.drawable.close) else painterResource(R.drawable.edit),
                    contentDescription = null,
                    tint = colorResource(R.color.black),
                )
            }
            IconButton(
                onClick = onDeletePass,
            ) {
                Icon(
                    painterResource(R.drawable.delete_ic_pass),
                    contentDescription = null,
                    tint = colorResource(R.color.black),

                    )
            }
        }
        if (editMode){
            Button(
                onClick = {
                    onEditPass(
                        RequestChange(
                            startDate = startDate,
                            finishDate = finishDate,
                            typeRequest = typeRequest,
                            photo = photo
                        )
                    )
                }, modifier = modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.red),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Сохранить",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

    }

}