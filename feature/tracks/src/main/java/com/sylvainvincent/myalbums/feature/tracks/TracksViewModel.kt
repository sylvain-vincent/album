package com.sylvainvincent.myalbums.feature.tracks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvainvincent.myalbums.core.domain.GetTracksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TracksViewModel @Inject constructor(
    getTracksUseCase: GetTracksUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            getTracksUseCase.invoke()
        }
    }

}