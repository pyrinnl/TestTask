package com.example.test.screens.main

internal sealed class MainScreenAction {

    internal data class OpenBlogDetailsFragment(val id: String) : MainScreenAction()
    internal data class ShowToast(val message: String) : MainScreenAction()
}