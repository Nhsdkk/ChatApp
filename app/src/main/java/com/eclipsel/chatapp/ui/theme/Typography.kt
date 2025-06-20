package com.eclipsel.chatapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.eclipsel.chatapp.R

val PoppinsFontFamily = FontFamily(
    Font(
        resId = R.font.poppins_medium,
        weight = FontWeight.Medium,
        style = FontStyle.Normal
    ),
    Font(
        resId = R.font.poppins_regular,
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    )
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 41.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 26.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 23.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 21.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 21.sp
    ),
    labelLarge = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    labelMedium = TextStyle(
        fontFamily = PoppinsFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ), 
)