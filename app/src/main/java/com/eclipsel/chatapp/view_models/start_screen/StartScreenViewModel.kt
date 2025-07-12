package com.eclipsel.chatapp.view_models.start_screen

import androidx.lifecycle.ViewModel
import androidx.navigation3.runtime.NavKey
import com.eclipsel.chatapp.navigation.GenderPickScreenRoute

interface IStartScreenViewModel {
    fun onLogin();
    fun onSignUp();
}

class StartScreenViewModel(
    val navigate: (NavKey) -> Unit
) : IStartScreenViewModel, ViewModel() {
    override fun onLogin() {
        TODO("Not yet implemented")
    }

    override fun onSignUp() {
        navigate(GenderPickScreenRoute)
    }

}