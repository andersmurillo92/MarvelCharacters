package com.grability.marvelcharacters.di

import dagger.Module
import dagger.Provides
import com.grability.marvelcharacters.data.interactor.IMarvelInteractor
import com.grability.marvelcharacters.data.interactor.MarvelInteractor
import com.grability.marvelcharacters.data.repository.IMarvelRepository
import com.grability.marvelcharacters.data.repository.MarvelRepository

@Module
class MarvelModule {

    @Provides
    fun provideInteractor(): IMarvelInteractor {
        return MarvelInteractor()
    }

    @Provides
    fun provideRepository(): IMarvelRepository {
        return MarvelRepository()
    }
}