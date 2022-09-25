package com.example.test.screens.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.test.compose.mainScreen.MainScreenCompose
import com.example.test.compose.theme.MyApplicationTheme
import com.example.test.utils.ComposeFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
internal class MainScreenFragment : ComposeFragment() {

    private val viewModel: MainScreenViewModel by viewModels()

    override fun composeViewFactory(): ComposeView = ComposeView(requireContext()).apply {
        setContent {
            MyApplicationTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val state: MainScreenViewModel.State? by viewModel.state.observeAsState(null)
                    state?.let {
                        MainScreenCompose(it)
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         lifecycleScope.launch {
            viewModel.action.collect {
                when(it){
                    is MainScreenAction.OpenBlogDetailsFragment -> routeBlogDetailsFragment(it.id)
                    is MainScreenAction.ShowToast -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun routeBlogDetailsFragment(blogId: String) {
        findNavController().navigate(
            MainScreenFragmentDirections.actionMainScreenFragmentToBlogDetailsFragment(blogId)
        )
    }
}