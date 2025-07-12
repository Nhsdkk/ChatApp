package com.eclipsel.chatapp.ui.screens

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.eclipsel.chatapp.models.Gender
import com.eclipsel.chatapp.ui.screens.common.Background
import com.eclipsel.chatapp.ui.theme.Background
import com.eclipsel.chatapp.ui.theme.ChatAppTheme
import com.eclipsel.chatapp.ui.theme.PoppinsFontFamily
import com.eclipsel.chatapp.view_models.birth_date_pick_screen.BirthDatePickScreenViewModel
import java.time.LocalDate
import com.eclipsel.chatapp.R
import com.eclipsel.chatapp.ui.screens.common.AvatarIcon
import com.eclipsel.chatapp.ui.screens.common.CustomDatePicker
import com.eclipsel.chatapp.ui.screens.common.IconTextButton

@Composable
fun BirthDatePickScreen(
    birthDatePickScreenViewModel: BirthDatePickScreenViewModel,
    modifier: Modifier = Modifier
) {
    val state = birthDatePickScreenViewModel.state.collectAsState()
    BirthDatePickScreenUi(
        gender = state.value.gender,
        birthDate = state.value.birthDate,
        onBirthDatePick = birthDatePickScreenViewModel::pickDate,
        onSubmit = birthDatePickScreenViewModel::onSubmit,
        modifier = modifier
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthDatePickScreenUi(
    gender: Gender,
    birthDate: LocalDate,
    onBirthDatePick: (LocalDate) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Background(
        gradient = MaterialTheme.colorScheme.Background,
        modifier = Modifier
            .fillMaxSize()
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(vertical = 10.dp, horizontal = 15.dp),
            contentAlignment = Alignment.Center
        ) {
            Row (
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                AvatarIcon(
                    online = true,
                    avatar = when (gender) {
                        Gender.Female -> R.drawable.female
                        Gender.Male -> R.drawable.male
                    },
                    modifier = Modifier
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = gender.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        Column (
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Your birthday",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "This way you and other people will be able to find each other by their age",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            CustomDatePicker(
                pickedDate = birthDate,
                onPickDate = onBirthDatePick,
            )   
        }
        IconTextButton(
            text = "Submit",
            icon = ImageVector.vectorResource(R.drawable.baseline_check_circle_outline_24),
            onClick = onSubmit
        )
    }
}

@Preview
@Composable
fun BirthDatePickScreenPreview() {
    ChatAppTheme {
        BirthDatePickScreenUi(
            gender = Gender.Male,
            birthDate = LocalDate.now(),
            onBirthDatePick = {},
            onSubmit = {}
        )
    }
}