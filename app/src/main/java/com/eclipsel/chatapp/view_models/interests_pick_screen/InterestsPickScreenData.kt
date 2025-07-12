package com.eclipsel.chatapp.view_models.interests_pick_screen

import com.eclipsel.chatapp.models.Gender
import com.eclipsel.chatapp.models.PickableInterest
import java.time.LocalDate

data class InterestsPickScreenData(
    val gender: Gender,
    val birthDate: LocalDate,
    val interests: List<PickableInterest>
)
