package com.example.test.screens.main.blog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.model.BlogDetailsRepository
import com.example.test.model.entities.blog.BlogDetails
import com.example.test.retrofit.main.entities.blog.BlogDetailsResponseEntity
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class BlogDetailsViewModel @AssistedInject constructor(
    @Assisted private val blogId: String,
    private val blogDetailsRepository: BlogDetailsRepository
) : ViewModel() {

    private val _state: MutableLiveData<State> = MutableLiveData(State.Loading)
    val state: LiveData<State> = _state

    private var loadingMainScreenJob: Job? = null

    init {
        resubscribe()
    }

    private fun onErrorBlogClick() {
        resubscribe()
    }

    private fun resubscribe() {
        _state.value = State.Loading
        loadingMainScreenJob?.cancel()
        loadingMainScreenJob = viewModelScope.launch(Dispatchers.IO) {
            when (val result = blogDetailsRepository.getBlogDetails(blogId)) {
                is BlogDetailsRepository.BlogDetailsResponse.Error -> {
                    _state.postValue(
                        State.Error(
                            error = result.message,
                            onTryResubscribe = { onErrorBlogClick() }
                        ))
                }
                is BlogDetailsRepository.BlogDetailsResponse.Success -> {
                    _state.postValue(
                        State.Loaded(result.blogDetails)
                    )
                }
            }
        }
    }

    override fun onCleared() {
        loadingMainScreenJob?.cancel()
        loadingMainScreenJob = null
        super.onCleared()
    }

    @AssistedFactory
    interface Factory {
        fun create(blogId: String): BlogDetailsViewModel
    }

    sealed interface State {
        object Loading : State
        data class Error(val error: String, val onTryResubscribe: () -> Unit) : State
        data class Loaded(val blogDetails: BlogDetails) : State
    }
}