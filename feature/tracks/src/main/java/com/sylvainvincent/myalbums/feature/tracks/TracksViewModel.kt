package com.sylvainvincent.myalbums.feature.tracks

import androidx.lifecycle.ViewModel
import com.sylvainvincent.myalbums.core.domain.GetTracksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TracksViewModel @Inject constructor(
    getTracksUseCase: GetTracksUseCase
) : ViewModel() {

    init {
        getTracksUseCase.invoke()
    }

}