package com.grability.marvelcharacters.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MarvelAdapter {

    private val baseUrl = "https://gateway.marvel.com/"
    private var marvelService: MarvelService? = null

    fun getMarvelService(): MarvelService? {

        // Create an interceptor and indicate log level to use
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        // Associate interceptor to requests
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        if (marvelService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build()) // <-- Use log level
                .build()
            marvelService = retrofit?.create<MarvelService>(MarvelService::class.java)
        }

        return marvelService
    }
}