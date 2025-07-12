package com.eclipsel.chatapp.ui.screens.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.eclipsel.chatapp.ui.theme.ChatAppTheme
import com.eclipsel.chatapp.R
import com.eclipsel.chatapp.ui.theme.Online

@PreviewLightDark
@Composable
fun AvatarIconPreview() {
    ChatAppTheme { 
        AvatarIcon(
            online = true,
            avatar = R.drawable.male
        )
    }
}

@Composable
fun AvatarIcon(
    online: Boolean,
    @DrawableRes avatar: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ){
        Image(
            painter = painterResource(avatar),
            contentDescription = null,
            modifier = Modifier
        )

        Box(
            modifier = Modifier
                .padding(3.dp)
                .size(12.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(if (online) MaterialTheme.colorScheme.Online else Color.Transparent)
        )   
    }
}