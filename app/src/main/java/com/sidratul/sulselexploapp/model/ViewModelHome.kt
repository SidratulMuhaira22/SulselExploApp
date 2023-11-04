package com.sidratul.sulselexploapp.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sidratul.sulselexploapp.data.DataWisata
import com.sidratul.sulselexploapp.data.WisataEntity
import com.sidratul.sulselexploapp.repository.WisataRepository
import com.sidratul.sulselexploapp.ui.panel.home.HomeUiState
import com.sidratul.sulselexploapp.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelHome @Inject constructor(private val wisataRepository: WisataRepository) : ViewModel() {
    private val _allTour = MutableStateFlow<ResultState<List<WisataEntity>>>(ResultState.Loading)
    val allTour = _allTour.asStateFlow()

    private val _homeUiState = mutableStateOf(HomeUiState())
    val homeUiState: State<HomeUiState> = _homeUiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            wisataRepository.getAllTour().collect { tour ->
                when (tour.isEmpty()) {
                    true -> wisataRepository.insertAllTour(DataWisata.dummy)
                    else -> _allTour.value = ResultState.Success(tour)
                }
            }
        }
    }

    private fun searchTour(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            wisataRepository.searchTour(query)
                .catch { _allTour.value = ResultState.Error(it.message.toString()) }
                .collect { _allTour.value = ResultState.Success(it) }
        }
    }

    fun updateFavoriteTour(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            wisataRepository.updateFavoriteTour(id, isFavorite)
        }
    }

    fun onQueryChange(query: String) {
        _homeUiState.value = _homeUiState.value.copy(query = query)
        searchTour(query)
    }
}