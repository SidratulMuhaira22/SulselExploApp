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
class ViewModelDetail @Inject constructor(private val wisataRepository: WisataRepository) : ViewModel() {
    private val _tour = MutableStateFlow<ResultState<WisataEntity>>(ResultState.Loading)
    val tour = _tour.asStateFlow()

    fun getTour(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            wisataRepository.getTour(id)
                .catch { _tour.value = ResultState.Error(it.message.toString()) }
                .collect { _tour.value = ResultState.Success(it) }
        }
    }

    fun updateFavoriteTour(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            wisataRepository.updateFavoriteTour(id, isFavorite)
        }
    }
}