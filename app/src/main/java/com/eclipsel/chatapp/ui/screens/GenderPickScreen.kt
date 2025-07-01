package com.eclipsel.chatapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.eclipsel.chatapp.R
import com.eclipsel.chatapp.models.Gender
import com.eclipsel.chatapp.ui.screens.common.Background
import com.eclipsel.chatapp.ui.screens.common.IconList
import com.eclipsel.chatapp.ui.screens.common.IconTextButton
import com.eclipsel.chatapp.ui.theme.Background
import com.eclipsel.chatapp.ui.theme.ChatAppTheme
import com.eclipsel.chatapp.view_models.gender_pick_screen.GenderPickScreenViewModel
import com.eclipsel.chatapp.view_models.gender_pick_screen.IGenderPickScreenViewModel

@Composable
fun GenderPickScreen(
    modifier: Modifier = Modifier,
    genderPickScreenViewModel: IGenderPickScreenViewModel = GenderPickScreenViewModel()
) {
    val gender = genderPickScreenViewModel.genderState.collectAsState()
    GenderPickScreenUi(
        currentGender = gender.value,
        onGenderChange = { genderPickScreenViewModel.onGenderChange(it) },
        onSubmit = { genderPickScreenViewModel.onGenderSubmit() },
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun GenderPickScreenUi(
    currentGender: Gender?,
    onGenderChange: (Gender) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Background(
        gradient = MaterialTheme.colorScheme.Background,
        modifier = modifier.fillMaxSize()
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.7f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconList()
            Spacer(modifier = modifier.height(25.dp))
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
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                genderButton(
                    gender = Gender.Female,
                    onClick = onGenderChange,
                    modifier = Modifier
                        .weight(0.4f)
                )
                Spacer(modifier = Modifier.width(10.dp))
                genderButton(
                    gender = Gender.Male,
                    onClick = onGenderChange,
                    modifier = Modifier
                        .weight(0.4f)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            IconTextButton(
                icon = ImageVector.vectorResource(R.drawable.baseline_check_circle_outline_24),
                text = "Submit",
                onClick = onSubmit
            )
        }
    }
}

@PreviewLightDark
@Composable
fun GenderPickScreenPreview() {
    ChatAppTheme {
        GenderPickScreenUi(
            currentGender = Gender.Female,
            onGenderChange = {},
            onSubmit = {}
        )
    }
}

@Composable
fun genderButton(
    gender: Gender,
    modifier: Modifier = Modifier,
    onClick: (Gender) -> Unit,
    enabled: Boolean = true
) {
    Button(
        enabled = enabled,
        onClick = { onClick(gender) },
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp
        ),
        modifier = modifier
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(5.dp)
        ) {
            Image(
                painter = painterResource(
                    when (gender) {
                        Gender.Female -> R.drawable.female
                        Gender.Male -> R.drawable.male
                    }
                ),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = when (gender) {
                    Gender.Female -> "I'm female"
                    Gender.Male -> "I'm male"
                },
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}