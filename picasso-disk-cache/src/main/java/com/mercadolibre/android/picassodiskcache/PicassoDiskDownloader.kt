package com.mercadolibre.android.picassodiskcache

import android.content.Context
import android.net.TrafficStats
import android.net.Uri
import android.os.StatFs
import com.squareup.picasso.Downloader
import com.squareup.picasso.NetworkPolicy
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.io.IOException
import java.util.*
import kotlin.math.max
import kotlin.math.min


internal class PicassoDiskDownloader constructor(builder: OkHttpClient.Builder) : Downloader {
    private val client: OkHttpClient
    private val cache: Cache?

    init {
        if (BuildConfig.DEBUG) {
            val loginInterceptor = HttpLoggingInterceptor()
            loginInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loginInterceptor)
        }
        builder.addNetworkInterceptor {
            val response = it.proceed(it.request())
            if (response.isSuccessful) {
                val newBuilder = response.newBuilder()
                newBuilder.request(replaceUrlInRequest(response.request(), it.call().request()))
                if (response.headers().get("cache-control") == null) {
                    newBuilder.header("cache-control", "max-age=31536000, immutable").build()
                }
                newBuilder.build()
            } else {
                response
            }
        }
        client = builder.build()
        cache = client.cache()
    }

    private fun replaceUrlInRequest(request: Request, anotherRequest: Request): Request {
        val url = anotherRequest.url()
        return request.newBuilder().url(url).build()
    }

    /**
     * Create new downloader that uses OkHttp. This will install an image cache into the specified
     * directory.
     *
     * @param cacheDir The directory in which the cache should be stored
     * @param maxSize The size limit for the cache.
     */
    constructor(cacheDir: File, maxSize: Long) : this(
        OkHttpClient.Builder().cache(
            Cache(
                cacheDir,
                maxSize
            )
        )
    )

    /**
     * Create new downloader that uses OkHttp. This will install an image cache into the specified
     * directory.
     *
     * @param cacheDir The directory in which the cache should be stored
     */
    constructor(cacheDir: File) : this(cacheDir, calculateDiskCacheSize(cacheDir))

    /**
     * Create new downloader that uses OkHttp. This will install an image cache into your application
     * cache directory.
     */
    constructor(context: Context) : this(createDefaultCacheDir(context))

    // Method needed to support picasso 2.+ latest version
    @Throws(IOException::class)
    fun load(request: Request): Response {
        val builder = request.newBuilder()
        // 0 means should read from cache, should write to cache, and no offline mode.
        addCacheControl(builder, 0)
        return client.newCall(builder.build()).execute()
    }

    @Throws(IOException::class)
    override fun load(uri: Uri, networkPolicy: Int): Downloader.Response? {
        val builder = Request.Builder().url(uri.toString())
        addCacheControl(builder, networkPolicy)
        TrafficStats.setThreadStatsTag(THREAD_STATS_TAG)

        return client.newCall(builder.build()).execute()?.let {
            it.body()?.run {
                Downloader.Response(
                    byteStream(),
                    it.cacheResponse() != null,
                    contentLength()
                )
            }
        }
    }

    private fun addCacheControl(builder: Request.Builder, networkPolicy: Int) {
        var cacheControl: CacheControl? = null
        if (networkPolicy != 0) {
            cacheControl = if (NetworkPolicy.isOfflineOnly(networkPolicy)) {
                CacheControl.FORCE_CACHE
            } else {
                val cacheBuilder = CacheControl.Builder()
                if (!NetworkPolicy.shouldReadFromDiskCache(networkPolicy)) {
                    cacheBuilder.noCache()
                }
                if (!NetworkPolicy.shouldWriteToDiskCache(networkPolicy)) {
                    cacheBuilder.noStore()
                }
                cacheBuilder.build()
            }
        }
        cacheControl?.let { builder.cacheControl(it) }
    }

    override fun shutdown() {
        cache?.apply {
            try {
                close()
            } catch (ignored: IOException) {
            }
        }
    }

    companion object {
        private const val PICASSO_CACHE = "picasso/cache"
        private const val MAX_DISK_CACHE_SIZE = 10L * 1024 * 1024 // 10MB
        private const val MIN_DISK_CACHE_SIZE = 5L * 1024 * 1024 // 5MB
        private val THREAD_STATS_TAG = Random().hashCode()

        private fun calculateDiskCacheSize(dir: File): Long {
            var size = MIN_DISK_CACHE_SIZE
            try {
                val statFs = StatFs(dir.absolutePath)
                val available = statFs.blockCountLong * statFs.blockSizeLong
                // Target 2% of the total space.
                size = available / 50
            } catch (ignored: IllegalArgumentException) {
            }
            // Bound inside min/max size for disk cache.
            return max(min(size, MAX_DISK_CACHE_SIZE), MIN_DISK_CACHE_SIZE)
        }

        private fun createDefaultCacheDir(context: Context): File {
            val cache = File(context.cacheDir, PICASSO_CACHE)
            cache.takeIf { !it.exists() }?.mkdirs()

            return cache
        }
    }
}