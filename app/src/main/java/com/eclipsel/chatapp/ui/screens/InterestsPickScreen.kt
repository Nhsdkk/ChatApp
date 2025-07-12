package com.eclipsel.chatapp.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.eclipsel.chatapp.R
import com.eclipsel.chatapp.ui.screens.common.Background
import com.eclipsel.chatapp.ui.theme.Background
import com.eclipsel.chatapp.ui.theme.ChatAppTheme
import com.eclipsel.chatapp.ui.theme.Outline

@PreviewLightDark
@Composable
fun InterestChipPreview() {
    ChatAppTheme {
        InterestChip(
            icon = R.drawable.drops, 
            title = "NFSW", 
            isSelected = true, 
            onClick = {}
        )
    }
}

@Composable
fun InterestChip(
    @DrawableRes icon: Int,
    title: String, 
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .shadow(
                elevation = if (isSelected) 20.dp else 0.dp,
                shape = RoundedCornerShape(size =  20.dp)
            )
            .clip(shape = RoundedCornerShape(size =  20.dp))
            .border(
                width =  1.dp, 
                color =  if (isSelected) Color.Transparent else MaterialTheme.colorScheme.Outline,
                shape = RoundedCornerShape(size =  20.dp)
            )
            .background(
                if (isSelected) MaterialTheme.colorScheme.surface else Color.Transparent
            )
            .padding(15.dp)
            .clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface
        ) 
    }
}

@Composable
fun InterestsPickScreen() {
    InterestsPickScreenUi()
}


@Composable
fun InterestsPickScreenUi() {
    Background(
        gradient = MaterialTheme.colorScheme.Background,
        modifier = Modifier
            .fillMaxSize()
    )
    
    Row {
        InterestChip(
            icon = R.drawable.drops,
            title = "NFSW",
            isSelected = false,
            onClick = {}
        )
        InterestChip(
            icon = R.drawable.drops,
            title = "NFSW",
            isSelected = true,
            onClick = {}
        )
    }
}

@PreviewLightDark
@Composable
fun InterestsPickScreenUiPreview(){
    ChatAppTheme { 
        InterestsPickScreenUi()
    }
}