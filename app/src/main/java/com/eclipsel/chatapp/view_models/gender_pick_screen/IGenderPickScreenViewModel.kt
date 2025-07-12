package com.eclipsel.chatapp.view_models.gender_pick_screen

import com.eclipsel.chatapp.models.Gender
import kotlinx.coroutines.flow.StateFlow

interface IGenderPickScreenViewModel {
    val screenState: StateFlow<GenderPickScreenData>
    fun onGenderChange(gender: Gender)
    fun onGenderSubmit()
}