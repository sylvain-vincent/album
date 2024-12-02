package com.sylvainvincent.myalbums.core.network

import android.content.Context
import android.net.ConnectivityManager
import com.sylvainvincent.myalbums.core.network.exception.NoNetworkException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class NetworkConnectionInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        if (isConnected()) {
            return chain.proceed(request)
        } else {
            throw NoNetworkException("Network Error")
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        return network != null
    }
}

