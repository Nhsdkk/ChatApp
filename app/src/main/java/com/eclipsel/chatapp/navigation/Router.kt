package com.eclipsel.chatapp.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.eclipsel.chatapp.models.Gender
import com.eclipsel.chatapp.ui.screens.BirthDatePickScreen
import com.eclipsel.chatapp.ui.screens.GenderPickScreen
import com.eclipsel.chatapp.ui.screens.InterestsPickScreen
import com.eclipsel.chatapp.ui.screens.StartScreen
import com.eclipsel.chatapp.utils.LocalDateSerializer
import com.eclipsel.chatapp.view_models.birth_date_pick_screen.BirthDatePickScreenViewModel
import com.eclipsel.chatapp.view_models.gender_pick_screen.GenderPickScreenViewModel
import com.eclipsel.chatapp.view_models.interests_pick_screen.InterestsPickScreenViewModel
import com.eclipsel.chatapp.view_models.start_screen.StartScreenViewModel
import kotlinx.serialization.Serializable
import java.security.InvalidKeyException
import java.time.LocalDate

@Serializable
data object StartScreenRoute : NavKey

@Serializable
data object GenderPickScreenRoute : NavKey

@Serializable
data class BirthDatePickScreenRoute(
    val gender: Gender
) : NavKey

@Serializable
data class InterestsPickScreenRoute(
    val gender: Gender,
    @Serializable(with = LocalDateSerializer::class)
    val birthDate: LocalDate
) : NavKey

@Composable
fun Router(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(StartScreenRoute)
    val navigate: (NavKey) -> Unit = { backStack.add(it) }

    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
            rememberSceneSetupNavEntryDecorator()
        ),
        entryProvider = { key ->
            when (key) {
                is StartScreenRoute -> NavEntry(key) {
                    StartScreen(
                        startScreenVM = viewModel<StartScreenViewModel>(
                            factory = object : ViewModelProvider.Factory {
                                @Suppress("UNCHECKED_CAST")
                                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                    return StartScreenViewModel (
                                        navigate = navigate
                                    ) as T
                                }
                            }
                        ),
                        modifier = modifier
                    )
                }

                is GenderPickScreenRoute -> NavEntry(key) {
                    GenderPickScreen(
                        genderPickScreenViewModel = viewModel<GenderPickScreenViewModel>(
                            factory = object : ViewModelProvider.Factory {
                                @Suppress("UNCHECKED_CAST")
                                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                    return GenderPickScreenViewModel(
                                        navigate = navigate,
                                        snackbarHostState = snackbarHostState
                                    ) as T
                                }
                            }
                        ),
                        modifier = modifier
                    )
                }

                is BirthDatePickScreenRoute -> NavEntry(key) {
                    BirthDatePickScreen(
                        birthDatePickScreenViewModel = viewModel<BirthDatePickScreenViewModel>(
                            factory = object : ViewModelProvider.Factory {
                                @Suppress("UNCHECKED_CAST")
                                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                    return BirthDatePickScreenViewModel(
                                        gender = key.gender,
                                        snackbarHostState = snackbarHostState,
                                        navigate = navigate
                                    ) as T
                                }
                            }
                        ),
                        modifier = modifier
                    )
                }

                is InterestsPickScreenRoute -> NavEntry(key) {
                    InterestsPickScreen(
                        viewModel = viewModel<InterestsPickScreenViewModel>(
                            factory = object: ViewModelProvider.Factory {
                                @Suppress("UNCHECKED_CAST")
                                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                    return InterestsPickScreenViewModel(
                                        gender = key.gender,
                                        birthDate = key.birthDate
                                    ) as T
                                }
                            }
                        ),
                        modifier = modifier
                    )
                }

                else -> throw InvalidKeyException("Invalid route passed")
            }
        }
    )
}