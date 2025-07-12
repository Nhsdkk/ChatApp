package com.eclipsel.chatapp.view_models.interests_pick_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eclipsel.chatapp.R
import com.eclipsel.chatapp.models.Gender
import com.eclipsel.chatapp.models.InterestDTO
import com.eclipsel.chatapp.models.PickableInterest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class InterestsPickScreenViewModel(
    gender: Gender,
    birthDate: LocalDate
) : IInterestsPickScreenViewModel, ViewModel() {
    private val _state = MutableStateFlow(
        InterestsPickScreenData(
            gender = gender,
            birthDate = birthDate,
            interests = listOf()
        )
    )
    override val state: StateFlow<InterestsPickScreenData>
        get() = _state

    init {
        viewModelScope.launch {
            loadInterests()
        }
    }

    fun getInterests(): List<InterestDTO> {
        return listOf(
            InterestDTO(id = "1", iconUrl =  R.drawable.drops, title = "NFSW"),
            InterestDTO(id = "1", iconUrl = R.drawable.in_love, title = "Love"),
            InterestDTO(id = "1", iconUrl = R.drawable.eyes, title = "Anime"),
            InterestDTO(id = "1", iconUrl = R.drawable.cool, title = "Party"),
            InterestDTO(id = "1", iconUrl = R.drawable.pepper, title = "Spicy")
        )
    }

    override suspend fun loadInterests() {
        val interests = getInterests()
        _state.value = _state.value.copy(
            interests = PickableInterest.fromListDTO(interests)
        )
    }

    override fun onInterestToggle(obj: PickableInterest) {
        val newInterests = _state.value.interests
            .map {
                if (it.itemId == obj.itemId) obj.copy(picked = !obj.picked) else it
            }

        _state.value = _state.value.copy(interests = newInterests)
    }

    override fun onSubmit() {
        TODO("Not yet implemented")
    }
}