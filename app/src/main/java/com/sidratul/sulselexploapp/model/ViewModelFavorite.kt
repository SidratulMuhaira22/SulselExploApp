package com.sidratul.sulselexploapp.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sidratul.sulselexploapp.data.WisataEntity
import com.sidratul.sulselexploapp.repository.WisataRepository
import com.sidratul.sulselexploapp.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelFavorite @Inject constructor(private val wisataRepository: WisataRepository) : ViewModel() {
    private val _allFavoriteTour = MutableStateFlow<ResultState<List<WisataEntity>>>(ResultState.Loading)
    val allFavoriteTour = _allFavoriteTour.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            wisataRepository.getAllFavoriteTour()
                .catch { _allFavoriteTour.value = ResultState.Error(it.message.toString()) }
                .collect { _allFavoriteTour.value = ResultState.Success(it) }
        }
    }

    fun updateFavoriteTour(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            wisataRepository.updateFavoriteTour(id, isFavorite)
        }
    }
}