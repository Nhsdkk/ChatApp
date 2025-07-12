package com.eclipsel.chatapp.view_models.gender_pick_screen

import com.eclipsel.chatapp.models.Gender

data class GenderPickScreenData(
    val gender: Gender? = null,
    val isFemalePicked: Boolean = false,
    val isMalePicked: Boolean = false
)
