package com.example.passtracker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.passtracker.R

val Mulish = FontFamily(
    Font(R.font.mulish_regular),
    Font(R.font.mulish_light),
    Font(R.font.mulish_extrabold),
    Font(R.font.mulish_bold),
    Font(R.font.mulish_semibold)
)
// Set of Material typography styles to start with
val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = Mulish,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 36.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Mulish,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Mulish,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Mulish,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Mulish,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp
    ),
//    labelSmall = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Medium,
//        fontSize = 11.sp,
//        lineHeight = 16.sp,
//        letterSpacing = 0.5.sp
//    )
//    */
)