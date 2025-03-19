package com.example.passtracker.app.ui.screen

import android.net.Uri
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passtracker.R
import com.example.passtracker.app.ui.component.DataInputField
import com.example.passtracker.domain.model.Profile
import com.example.passtracker.domain.model.Request
import com.example.passtracker.domain.model.RequestChange
import java.io.InputStream

@Composable
fun PassScreenContent(
    request: Request,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onDeletePass: () -> Unit = {},
    onEditPass: (RequestChange) -> Unit = {}
) {
    val context = LocalContext.current
    var startDate by remember { mutableStateOf(parseAndFormatIsoDate(request.startDate)) }
    var finishDate by remember { mutableStateOf(parseAndFormatIsoDate(request.finishDate)) }
    var typeRequest by remember { mutableStateOf(request.typeRequest) }
    var photo by remember {
        mutableStateOf<String?>(request.photo)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            try {
                val inputStream: InputStream? = context.contentResolver.openInputStream(it)
                val byteArray = inputStream?.readBytes()
                inputStream?.close()

                if (byteArray != null) {
                    val base64String = Base64.encodeToString(byteArray, Base64.DEFAULT)
                    photo = base64String
                }
            } catch (e: Exception) {
                e.printStackTrace()
                photo = null
            }
        }
    }
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp, top = 24.dp)
        ) {
            DataInputField(
                hint = "Дата начала",
                value = startDate,
                iconId = R.drawable.calendar_pass,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                startDate = it
            }
            DataInputField(
                hint = "Дата окончания",
                value = finishDate,
                iconId = R.drawable.calendar_pass,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                finishDate = it
            }
            if (photo != null) {
                val photoBitmap by remember(photo) {
                    derivedStateOf {
                        base64StringToImageBitmap(photo!!)
                    }
                }
                if (photoBitmap != null) {
                    Image(
                        bitmap = photoBitmap!!,
                        contentDescription = "Выбранное фото",
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(top = 24.dp, bottom = 24.dp).padding(top = 24.dp).clickable {
                                val uri = base64ToUri(context, photo!!)
                                if (uri != null) {
                                    openImageInGallery(context, uri.toString())
                                }
                            }
                    )
                } else {
                    Text(
                        text = "Не удалось загрузить фото",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            } else if (editMode) {
                Icon(
                    painter = painterResource(R.drawable.add_circle),
                    contentDescription = null,
                    modifier = Modifier.weight(1f).clickable {
                        launcher.launch("image/*")
                    }.align(Alignment.CenterHorizontally),
                    tint = colorResource(R.color.dark_gray),
                )
            }
            if (editMode) {
                Button(
                    onClick = {
                        onEditPass(
                            RequestChange(
                                startDate = formatToIsoDate(startDate),
                                finishDate = formatToIsoDate(finishDate),
                                typeRequest = typeRequest,
                                photo = photo.toString()
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

}