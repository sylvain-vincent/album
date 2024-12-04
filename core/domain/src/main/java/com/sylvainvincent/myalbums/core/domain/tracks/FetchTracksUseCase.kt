package com.sylvainvincent.myalbums.core.domain.tracks

import com.sylvainvincent.myalbums.core.model.Track

interface FetchTracksUseCase : suspend () -> Result<List<Track>>
