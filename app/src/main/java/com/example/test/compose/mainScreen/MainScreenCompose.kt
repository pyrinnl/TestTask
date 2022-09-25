package com.example.test.compose.mainScreen

import androidx.compose.runtime.Composable
import com.example.test.screens.main.MainScreenViewModel

/**
 * Опередляет стейт экрана
 * и рисует соотвествующие вью
 */
@Composable
internal fun MainScreenCompose(state:MainScreenViewModel.State) {
    when(state){
        is MainScreenViewModel.State.Error -> {
            ErrorViewState(
                error = state.error,
                onTryResubscribe = state.onTryResubscribe
            )
        }
        is MainScreenViewModel.State.Loaded -> {
            LoadedViewState(mainPage = state.mainPage)
        }
        MainScreenViewModel.State.Loading -> {
            LoadingViewState()
        }
    }
}