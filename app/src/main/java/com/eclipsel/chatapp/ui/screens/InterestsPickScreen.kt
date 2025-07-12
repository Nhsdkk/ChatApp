package com.eclipsel.chatapp.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.eclipsel.chatapp.R
import com.eclipsel.chatapp.models.Gender
import com.eclipsel.chatapp.models.PickableInterest
import com.eclipsel.chatapp.ui.screens.common.Background
import com.eclipsel.chatapp.ui.screens.common.IconTextButton
import com.eclipsel.chatapp.ui.screens.common.ProfilePopup
import com.eclipsel.chatapp.ui.theme.Background
import com.eclipsel.chatapp.ui.theme.ChatAppTheme
import com.eclipsel.chatapp.ui.theme.Outline
import com.eclipsel.chatapp.view_models.interests_pick_screen.IInterestsPickScreenViewModel
import java.time.LocalDate

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
                shape = RoundedCornerShape(size = 20.dp)
            )
            .clip(shape = RoundedCornerShape(size = 20.dp))
            .border(
                width = 1.dp,
                color = if (isSelected) Color.Transparent else MaterialTheme.colorScheme.Outline,
                shape = RoundedCornerShape(size = 20.dp)
            )
            .clickable(onClick = onClick)
            .background(
                if (isSelected) MaterialTheme.colorScheme.surface else Color.Transparent
            )
            .padding(15.dp)
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
fun InterestsPickScreen(
    viewModel: IInterestsPickScreenViewModel,
    modifier : Modifier = Modifier
) {
    val state = viewModel.state.collectAsState()
    InterestsPickScreenUi(
        interests = state.value.interests,
        gender = state.value.gender,
        birthDate = state.value.birthDate,
        onInterestToggle = viewModel::onInterestToggle,
        onSubmit = viewModel::onSubmit,
        modifier = modifier
    )
}


@Composable
fun InterestsPickScreenUi(
    interests: List<PickableInterest>,
    gender: Gender,
    birthDate: LocalDate,
    onInterestToggle: (PickableInterest) -> Unit,
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
        ProfilePopup(
            avatar = when (gender) {
                Gender.Female -> R.drawable.female
                Gender.Male -> R.drawable.male
            },
            gender = gender,
            birthDate = birthDate
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "What are you into?",
                    style = MaterialTheme.typography.displayLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(20.dp))
                FlowRow(
                    itemVerticalAlignment = Alignment.CenterVertically,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    for (it in interests) {
                        InterestChip(
                            icon = it.iconUrl,
                            title = it.title,
                            isSelected = it.picked,
                            onClick = { onInterestToggle(it) }
                        )
                    }
                }
            }
        }

        IconTextButton(
            text = "Complete",
            icon = ImageVector.vectorResource(R.drawable.baseline_check_circle_outline_24),
            onClick = onSubmit
        )
    }
}

@PreviewLightDark
@Composable
fun InterestsPickScreenUiPreview(){
    val interests = listOf(
        PickableInterest(id = "1", iconUrl =  R.drawable.drops, title = "NFSW", itemId = 1),
        PickableInterest(id = "1", iconUrl = R.drawable.in_love, title = "Love", itemId = 2, picked = true),
        PickableInterest(id = "1", iconUrl = R.drawable.eyes, title = "Anime", itemId = 3, picked = true),
        PickableInterest(id = "1", iconUrl = R.drawable.cool, title = "Party", itemId = 4),
        PickableInterest(id = "1", iconUrl = R.drawable.pepper, title = "Spicy", itemId = 5)
    )

    ChatAppTheme { 
        InterestsPickScreenUi(
            interests = interests,
            gender = Gender.Female,
            birthDate = LocalDate.now().minusYears(18),
            onInterestToggle = { },
            onSubmit = {}
        )
    }
}