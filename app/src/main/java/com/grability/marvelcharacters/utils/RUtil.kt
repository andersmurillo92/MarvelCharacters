package com.grability.marvelcharacters.utils

import com.grability.marvelcharacters.MarvelCharactersApp

class RUtil {
    companion object {
        fun rString(resId: Int): String {
            return MarvelCharactersApp.getInstance().getString(resId)
        }
    }
}