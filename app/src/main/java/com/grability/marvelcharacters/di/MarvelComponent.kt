package com.grability.marvelcharacters.di

import dagger.Component
import com.grability.marvelcharacters.views.home.HomeViewModel
import com.grability.marvelcharacters.data.interactor.MarvelInteractor

@Component(modules = arrayOf(MarvelModule::class))
interface MarvelComponent {
    fun inject(homeViewModel: HomeViewModel)
    fun inject(marvelInteractor: MarvelInteractor)
}