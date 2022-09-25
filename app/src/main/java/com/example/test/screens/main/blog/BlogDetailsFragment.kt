package com.example.test.screens.main.blog


import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.navArgs
import com.example.test.compose.blogScreen.BlogDetailsScreenCompose
import com.example.test.compose.theme.MyApplicationTheme
import com.example.test.utils.ComposeFragment
import com.example.test.utils.viewModelCreator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
internal class BlogDetailsFragment : ComposeFragment() {

    @Inject
    lateinit var factory: BlogDetailsViewModel.Factory
    private val viewModel: BlogDetailsViewModel by viewModelCreator {
        factory.create(args.blogId)
    }

    private val args: BlogDetailsFragmentArgs by navArgs()

    override fun composeViewFactory(): ComposeView = ComposeView(requireContext()).apply {
        setContent {
            MyApplicationTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val state: BlogDetailsViewModel.State? by viewModel.state.observeAsState(null)
                    state?.let {
                        BlogDetailsScreenCompose(it)
                    }
                }
            }
        }
    }

}