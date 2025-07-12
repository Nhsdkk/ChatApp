package com.eclipsel.chatapp.view_models.interests_pick_screen

import com.eclipsel.chatapp.models.PickableInterest
import kotlinx.coroutines.flow.StateFlow

interface IInterestsPickScreenViewModel {
    val state : StateFlow<InterestsPickScreenData>

    suspend fun loadInterests()
    fun onInterestToggle(obj: PickableInterest)
    fun onSubmit()
}