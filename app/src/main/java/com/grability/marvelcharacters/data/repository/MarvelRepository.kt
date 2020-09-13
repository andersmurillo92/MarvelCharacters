package com.grability.marvelcharacters.data.repository

import com.grability.marvelcharacters.data.dto.ResponseDTO
import com.grability.marvelcharacters.network.MarvelAdapter
import io.reactivex.Observable

interface IMarvelRepository {
    fun getCharacters(ts: Long, apikey: String, hash: String): Observable<ResponseDTO>?
}

class MarvelRepository: IMarvelRepository {
    override fun getCharacters(ts: Long, apikey: String, hash: String): Observable<ResponseDTO>? {
        return MarvelAdapter().getMarvelService()?.getCharacters(ts, apikey, hash)
    }
}