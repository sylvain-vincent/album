package com.sylvainvincent.myalbums.core.domain.tracks

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import javax.inject.Inject

internal class HaveInternetConnectionUseCaseImpl @Inject constructor(
    private val connectivityManager: ConnectivityManager
): HaveInternetConnectionUseCase {

    @SuppressLint("MissingPermission")
    override suspend fun invoke(): Boolean {
        val network = connectivityManager.activeNetwork
        return network != null
    }
}