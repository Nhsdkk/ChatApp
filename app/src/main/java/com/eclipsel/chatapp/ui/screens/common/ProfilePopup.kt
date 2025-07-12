package com.eclipsel.chatapp.ui.screens.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.eclipsel.chatapp.models.Gender
import java.time.LocalDate
import java.time.Period

@Composable
fun ProfilePopup(
    @DrawableRes avatar: Int,
    name: String? = null,
    gender: Gender? = null,
    birthDate: LocalDate? = null,
) {
    val text = listOf(
        name ?: "",
        gender?.toString() ?: "",
        if (birthDate == null) "" else Period.between(birthDate, LocalDate.now()).years.toString()
    )
        .filter { it != "" }
        .joinToString(separator = ", ")
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(vertical = 10.dp, horizontal = 15.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AvatarIcon(
                online = true,
                avatar = avatar,
                modifier = Modifier
                    .size(40.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}