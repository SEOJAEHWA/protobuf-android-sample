package com.jaehwa.sample.protobuf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaehwa.sample.protobuf.core.data.repository.SampleDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val sampleDataRepository: SampleDataRepository,
) : ViewModel() {

    val count: StateFlow<Int> = sampleDataRepository.sampleData
        .map { it.data.toInt() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0
        )

    fun setCount(count: Int) {
        viewModelScope.launch {
            sampleDataRepository.setData(count.toString())
        }
    }

    fun clearCount() {
        viewModelScope.launch {
            sampleDataRepository.setData("0")
        }
    }
}