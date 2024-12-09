package com.example.demoapp

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestClient private constructor() {

    companion object {
        private const val BASE_URL = "https://navkiraninfotech.com/g-mee-api/api/v1/"
        private const val TIMEOUT_SECONDS = 30L

        // Singleton instance of Retrofit
        @Volatile
        private var retrofitInstance: Retrofit? = null

        fun getInstance(): Retrofit {
            return retrofitInstance ?: synchronized(this) {
                retrofitInstance ?: buildRetrofit().also { retrofitInstance = it }
            }
        }

        private fun buildRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(buildOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private fun buildOkHttpClient(): OkHttpClient {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build()
        }
    }
}
