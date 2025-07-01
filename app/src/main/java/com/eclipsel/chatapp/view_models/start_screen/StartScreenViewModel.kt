package com.eclipsel.chatapp.view_models.start_screen

import androidx.lifecycle.ViewModel

interface IStartScreenViewModel {
    fun onLogin();
    fun onSignUp();
}

class StartScreenViewModel : IStartScreenViewModel, ViewModel() {
    override fun onLogin() {
        TODO("Not yet implemented")
    }

    override fun onSignUp() {
        TODO("Not yet implemented")
    }

}