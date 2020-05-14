package com.mercadolibre.android.picassodiskcache

import android.net.TrafficStats
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class StrictModeInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        TrafficStats.setThreadStatsTag(TAG)
        return chain.proceed(chain.request())
    }

    companion object {
        private const val TAG = 1985
    }
}