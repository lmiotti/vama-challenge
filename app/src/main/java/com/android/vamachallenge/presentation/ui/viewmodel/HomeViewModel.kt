package com.android.vamachallenge.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.vamachallenge.di.IoDispatcher
import com.android.vamachallenge.domain.usecase.GetAlbumListUseCase
import com.android.vamachallenge.models.Resource
import com.android.vamachallenge.presentation.ui.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    init {
        _state.update { _state.value.copy(isLoading = true) }

        viewModelScope.launch(dispatcher) {
            val response = getAlbumListUseCase()
            if (response is Resource.Success) {
                _state.update {
                    _state.value.copy(
                        isLoading = false,
                        albums = response.data ?: listOf()
                    )
                }
            } else {
                _state.update { _state.value.copy(error = "ERROR") }
            }
        }
    }
}