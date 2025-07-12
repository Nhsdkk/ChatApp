package com.eclipsel.chatapp.view_models.birth_date_pick_screen

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eclipsel.chatapp.models.Gender
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period

data class BirthDatePickScreenState(
    val gender: Gender,
    val birthDate: LocalDate
)

interface IBirthDateScreenViewModel {
    val state: StateFlow<BirthDatePickScreenState>
    fun pickDate(pickedDate: LocalDate)
    fun onSubmit()
}

class BirthDatePickScreenViewModel(
    gender: Gender,
    val snackbarHostState: SnackbarHostState
) : IBirthDateScreenViewModel, ViewModel() {
    private val _state = MutableStateFlow(
        BirthDatePickScreenState(
            gender = gender,
            birthDate = LocalDate.now()
        )
    )

    override val state: StateFlow<BirthDatePickScreenState>
        get() = _state

    override fun pickDate(pickedDate: LocalDate) {
        _state.value = _state.value.copy(birthDate = pickedDate)
    }

    override fun onSubmit() {
        if (_state.value.birthDate.isAfter(LocalDate.now())) {
            viewModelScope.launch {
                snackbarHostState
                    .showSnackbar(
                        message = "Invalid birthdate as it's after today's date",
                        duration = SnackbarDuration.Short,
                        withDismissAction = true
                    )
            }
            return
        }

        if (Period.between(_state.value.birthDate, LocalDate.now()).years < 18) {
            viewModelScope.launch {
                snackbarHostState
                    .showSnackbar(
                        message = "You are not old enough to register",
                        duration = SnackbarDuration.Short,
                        withDismissAction = true
                    )
            }
            return
        }
    }
}