package com.grability.marvelcharacters

import android.app.Application
import android.content.Context

class MarvelCharactersApp: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: MarvelCharactersApp

        fun getInstance(): MarvelCharactersApp {
            return instance
        }

        fun getAppContext(): Context {
            return instance.applicationContext
        }
    }
}