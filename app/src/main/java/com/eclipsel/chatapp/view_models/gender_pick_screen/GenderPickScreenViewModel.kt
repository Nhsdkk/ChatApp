package com.eclipsel.chatapp.view_models.gender_pick_screen

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavKey
import com.eclipsel.chatapp.models.Gender
import com.eclipsel.chatapp.navigation.BirthDatePickScreenRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GenderPickScreenViewModel(
    val navigate: (NavKey) -> Unit,
    val snackbarHostState: SnackbarHostState
) : IGenderPickScreenViewModel, ViewModel() {
    private val _state: MutableStateFlow<GenderPickScreenData> = MutableStateFlow(GenderPickScreenData())
    override val screenState: StateFlow<GenderPickScreenData> get() = _state

    override fun onGenderChange(gender: Gender) {
        _state.value = GenderPickScreenData(
            gender = gender,
            isMalePicked = gender == Gender.Male,
            isFemalePicked = gender == Gender.Female
        )
    }

    override fun onGenderSubmit() {
        if (_state.value.gender == null) {
            viewModelScope.launch { 
                snackbarHostState
                    .showSnackbar(
                        message = "The gender is empty. Fill it up!",
                        duration = SnackbarDuration.Short,
                        withDismissAction = true
                    )
            }
            return
        }
        navigate(BirthDatePickScreenRoute(_state.value.gender as Gender))
    }
}