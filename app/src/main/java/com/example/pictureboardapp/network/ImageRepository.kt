package com.example.pictureboardapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ImageRepository {
    private lateinit var service: ImageService

    fun getImageService(): ImageService {
        if (!::service.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://loremflickr.com")
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            service = retrofit.create(ImageService::class.java)

        }
        return service
    }


    private fun getClient(): OkHttpClient {

        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .callTimeout(3, TimeUnit.SECONDS)
        return client.build()
    }
}