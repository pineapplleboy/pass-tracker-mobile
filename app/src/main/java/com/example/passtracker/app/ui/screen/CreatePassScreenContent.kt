package com.example.passtracker.app.ui.screen

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passtracker.R
import com.example.passtracker.app.ui.component.DataInputField
import com.example.passtracker.app.ui.component.InputField
import com.example.passtracker.domain.model.Request
import com.example.passtracker.domain.model.RequestChange
import com.example.passtracker.domain.model.RequestCreate
import com.example.passtracker.domain.model.TypeRequest
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import com.example.passtracker.app.ui.component.TypeDataField
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.util.*

@Composable
fun CreatePassScreenContent(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onCreatePass: (RequestCreate) -> Unit = {}
) {
    val context = LocalContext.current
    var startDate by remember { mutableStateOf("") }
    var finishDate by remember { mutableStateOf("") }
    var typeRequest by remember { mutableStateOf("") }
    var photo by remember { mutableStateOf<String?>(null) }
    val access by remember { derivedStateOf { startDate.isNotBlank() && finishDate.isNotBlank() && typeRequest.isNotBlank() } }

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
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp)) {
            TypeDataField(
                hint = "Тип пропуска",
                value = typeRequest,
                iconId = R.drawable.down_arrow
            ) {
                typeRequest = it
            }
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
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        Image(
                            bitmap = photoBitmap!!,
                            contentDescription = "Выбранное фото",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 24.dp).clickable {
                                    val uri = base64ToUri(context, photo!!)
                                    if (uri != null) {
                                        openImageInGallery(context, uri.toString())
                                    }
                                }

                        )
                        Icon(
                            painter = painterResource(R.drawable.close),
                            contentDescription = null,
                            modifier = Modifier.align(Alignment.TopEnd).padding(top = 24.dp).clickable { photo = null }
                        )
                    }
                } else {
                    Text(
                        text = "Не удалось загрузить фото",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            } else {
                Icon(
                    painter = painterResource(R.drawable.add_circle),
                    contentDescription = null,
                    modifier = Modifier.weight(1f).clickable {
                        launcher.launch("image/*")
                    }.align(Alignment.CenterHorizontally),
                    tint = colorResource(R.color.dark_gray),
                )
            }

            Button(
                onClick = {
                    onCreatePass(
                        RequestCreate(
                            startDate = formatToIsoDate(startDate),
                            finishDate = formatToIsoDate(finishDate),
                            typeRequest = when (typeRequest) {
                                "Учебная деятельность" -> TypeRequest.EducationalActivity
                                "По болезни" -> TypeRequest.Disease
                                "По семейным обстоятельствам" -> TypeRequest.FamilyCircumstances
                                else -> TypeRequest.EducationalActivity
                            },
                            photo = photo.toString()
                        )
                    )
                }, modifier = modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.red),
                    contentColor = Color.White
                ),
                enabled = access
            ) {
                Text(
                    text = "Сохранить",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

    }


}

fun formatToIsoDate(formattedDate: String): String {
    val inputFormat = SimpleDateFormat("d MMMM yyyy HH:mm", Locale("ru"))
    val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    val date = inputFormat.parse(formattedDate)
    return outputFormat.format(date)
}

fun parseAndFormatIsoDate(isoDate: String): String {
    val isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    val displayFormat = SimpleDateFormat("d MMMM yyyy HH:mm", Locale("ru"))
    val date = isoFormat.parse(isoDate)
    return displayFormat.format(date)
}

fun base64StringToImageBitmap(base64String: String?): ImageBitmap? {
    return try {
        val byteArray = Base64.decode(base64String, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        bitmap?.asImageBitmap()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun base64ToUri(context: Context, base64String: String): Uri? {
    return try {
        val byteArray = Base64.decode(base64String, Base64.DEFAULT)

        val tempFile = File(context.cacheDir, "temp_image_${System.currentTimeMillis()}.jpg")
        FileOutputStream(tempFile).use { outputStream ->
            outputStream.write(byteArray)
        }

        FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            tempFile
        )
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun openImageInGallery(context: Context, imageUri: String) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(Uri.parse(imageUri), "image/*")
        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
    }
    context.startActivity(intent)
}