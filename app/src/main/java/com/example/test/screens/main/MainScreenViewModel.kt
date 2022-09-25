package com.example.test.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.model.MainScreenRepository
import com.example.test.model.entities.main.MainPage
import com.example.test.retrofit.main.views.BlogItemResponseEntity
import com.example.test.retrofit.main.views.ObjectResponseEntity
import com.example.test.retrofit.main.views.RoomsResponseEntity
import com.example.test.retrofit.main.views.Stub
import com.example.test.retrofit.main.views.ToursResponseEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MainScreenViewModel @Inject constructor(
    private val mainScreenRepository: MainScreenRepository
) : ViewModel() {

    private val _state: MutableLiveData<State> = MutableLiveData(State.Loading)
    val state: LiveData<State> = _state

    private var loadingMainScreenJob: Job? = null

    private val _action: Channel<MainScreenAction> = Channel(Channel.BUFFERED)
    val action: Flow<MainScreenAction> = _action.receiveAsFlow()

    init {
        resubscribe()
    }

    private fun onErrorMainClick() {
        resubscribe()
    }

    private fun resubscribe() {
        _state.value = State.Loading
        loadingMainScreenJob?.cancel()
        loadingMainScreenJob = viewModelScope.launch(Dispatchers.IO) {
            when (val result = mainScreenRepository.getMainPage()) {
                is MainScreenRepository.MainResponse.Error -> {
                    _state.postValue(State.Error(
                        error = result.message,
                        onTryResubscribe = { onErrorMainClick() }
                    ))
                }
                is MainScreenRepository.MainResponse.Success -> {
                    _state.postValue(
                        State.Loaded(
                            mainPage = result.mainPage.also { mainPage ->
                                mainPage.viewsList.forEach { mainDataViews ->
                                    mainDataViews.onClick = {
                                        when (mainDataViews.mainScreenViewsList){
                                            is BlogItemResponseEntity -> _action.trySend(MainScreenAction.OpenBlogDetailsFragment(it.toString()))
                                            is ObjectResponseEntity,
                                            is RoomsResponseEntity,
                                            Stub,
                                            is ToursResponseEntity -> sendActionToast()
                                        }
                                    }
                                }
                            }
                        )
                    )
                }
            }
        }
    }

    /**
     * Простая заглушка для элементов при клике.
     */
    private fun sendActionToast(){
        _action.trySend(MainScreenAction.ShowToast("Неподдерживаемый сценарий"))
    }

    override fun onCleared() {
        loadingMainScreenJob?.cancel()
        loadingMainScreenJob = null
        super.onCleared()
    }

    sealed interface State {
        object Loading : State
        data class Error(val error: String, val onTryResubscribe: () -> Unit) : State
        data class Loaded(val mainPage: MainPage) : State
    }

}