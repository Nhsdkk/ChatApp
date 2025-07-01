package com.eclipsel.chatapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.eclipsel.chatapp.R
import com.eclipsel.chatapp.ui.theme.BlackTransparent50
import com.eclipsel.chatapp.ui.theme.BlueSolidPrimary
import com.eclipsel.chatapp.ui.theme.ChatAppTheme
import com.eclipsel.chatapp.ui.theme.PinkSolidPrimary
import com.eclipsel.chatapp.ui.theme.WhiteSolidPrimary
import com.eclipsel.chatapp.ui.theme.WhiteTransparent50
import com.eclipsel.chatapp.view_models.start_screen.IStartScreenViewModel
import com.eclipsel.chatapp.view_models.start_screen.StartScreenViewModel
import kotlin.random.Random

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    startScreenVM: IStartScreenViewModel = StartScreenViewModel()
) {
    StartScreenUi(
        modifier = modifier,
        onLogin = { startScreenVM.onLogin() },
        onSignup = { startScreenVM.onSignUp() }
    )
}

@Composable
fun StartScreenUi(
    onLogin: () -> Unit,
    onSignup: () -> Unit,
    modifier: Modifier = Modifier
) {
    Background()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        Column(
            modifier = Modifier
                .weight(0.9f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconList()
            Spacer(modifier = Modifier.height(25.dp))
            Image(
                painter = painterResource(R.drawable.app_icon),
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "Anonymous chats with other people",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Meet random people in chats. Gift or receive tokens.",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
        IconTextButton(
            onClick = onLogin,
            text = "Sign in to account",
            icon = ImageVector.vectorResource(R.drawable.baseline_login_24)
        )
        Spacer(modifier = Modifier.height(20.dp))
        IconTextButton(
            onClick = onSignup,
            text = "Create an account",
            icon = ImageVector.vectorResource(R.drawable.baseline_person_add_alt_24)
        )

    }
}


@Composable
fun IconTextButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonColors(
            containerColor = WhiteSolidPrimary,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContentColor = BlackTransparent50,
            disabledContainerColor = WhiteTransparent50
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = text,
                modifier = Modifier.width(200.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun IconList(modifier: Modifier = Modifier) {
    val offsets = (1..9).map { Pair(Random.nextInt(0, 25), Random.nextInt(0, 15)) }
    val icons = listOf(
        R.drawable.fire,
        R.drawable.cool,
        R.drawable.eyes,
        R.drawable.monkey,
        R.drawable.drops,
        R.drawable.in_love,
        R.drawable.mouth,
        R.drawable.person_exhausted,
        R.drawable.valentine
    )

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        for (i in 0..2) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (j in 0..2) {
                    Image(
                        painter = painterResource(icons[i * 3 + j]),
                        modifier = Modifier
                            .padding(
                                horizontal = offsets[i * 3 + j].first.dp,
                                vertical = offsets[i * 3 + j].second.dp
                            )
                            .height(32.dp)
                            .width(32.dp),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = false)
@Composable
fun Background(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        PinkSolidPrimary,
                        BlueSolidPrimary
                    ),
                    start = Offset.Zero
                ),
                alpha = 0.9f
            )
    )
}

@Preview
@Composable
fun StartScreenUiPreview() {
    ChatAppTheme(darkTheme = false) {
        StartScreenUi(
            onLogin = {},
            onSignup = {}
        )
    }
}