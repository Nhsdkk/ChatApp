package com.eclipsel.chatapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.eclipsel.chatapp.R
import com.eclipsel.chatapp.ui.screens.common.Background
import com.eclipsel.chatapp.ui.screens.common.IconList
import com.eclipsel.chatapp.ui.screens.common.IconTextButton
import com.eclipsel.chatapp.ui.theme.Background
import com.eclipsel.chatapp.ui.theme.ChatAppTheme
import com.eclipsel.chatapp.view_models.start_screen.IStartScreenViewModel
import com.eclipsel.chatapp.view_models.start_screen.StartScreenViewModel

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
    Background(
        gradient = MaterialTheme.colorScheme.Background,
        modifier = modifier.fillMaxSize()
    )
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
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Meet random people in chats. Gift or receive tokens.",
                color = MaterialTheme.colorScheme.onBackground,
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

@PreviewLightDark
@Composable
fun StartScreenUiPreview() {
    ChatAppTheme() {
        StartScreenUi(
            onLogin = {},
            onSignup = {}
        )
    }
}