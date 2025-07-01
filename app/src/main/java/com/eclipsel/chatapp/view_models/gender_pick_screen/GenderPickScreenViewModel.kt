package com.eclipsel.chatapp.view_models.gender_pick_screen

import androidx.lifecycle.ViewModel
import com.eclipsel.chatapp.models.Gender
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface IGenderPickScreenViewModel {
    val genderState: StateFlow<Gender?>
    fun onGenderChange(gender: Gender)
    fun onGenderSubmit()
}

class GenderPickScreenViewModel : IGenderPickScreenViewModel, ViewModel() {
    private var _gender : MutableStateFlow<Gender?> = MutableStateFlow(null)
    override val genderState: StateFlow<Gender?> = _gender
    
    override fun onGenderChange(gender: Gender) {
        _gender.value = gender
    }

    override fun onGenderSubmit() {
        TODO("Not yet implemented")
    }
}