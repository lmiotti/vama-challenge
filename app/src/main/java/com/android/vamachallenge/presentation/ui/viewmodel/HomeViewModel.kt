package com.android.vamachallenge.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.vamachallenge.di.IoDispatcher
import com.android.vamachallenge.domain.model.Album
import com.android.vamachallenge.domain.usecase.GetAlbumListUseCase
import com.android.vamachallenge.domain.model.Resource
import com.android.vamachallenge.presentation.ui.intent.HomeIntent
import com.android.vamachallenge.presentation.ui.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAlbumListUseCase: GetAlbumListUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState>
        get() = _state

    private val _showError = MutableSharedFlow<Boolean>()
    val showError: SharedFlow<Boolean>
        get() = _showError

    init {
        getAlbums()
    }

    fun handleIntent(intent: HomeIntent) {
        if (intent is HomeIntent.OnRetryClicked) {
            getAlbums()
        }
    }
    private fun getAlbums() {
        viewModelScope.launch(dispatcher) {
            getAlbumListUseCase().collectLatest {
                handleAlbumsResponse(it)
            }
        }
    }

    private suspend fun handleAlbumsResponse(response: Resource<List<Album>>) {
        when(response) {
            is Resource.Loading -> _state.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }
            is Resource.Success -> _state.update {
                it.copy(
                    isLoading = false,
                    albums = response.data ?: listOf(),
                    error = null
                )
            }
            is Resource.Failure -> {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = response.error?.error
                    )
                }
                _showError.emit(true)
            }
        }
    }
}