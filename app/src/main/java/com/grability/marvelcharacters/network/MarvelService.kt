package com.grability.marvelcharacters.network

import com.grability.marvelcharacters.data.dto.ResponseDTO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("v1/public/characters")
    fun getCharacters(@Query("ts") ts: Long,
                      @Query("apikey") apikey: String,
                      @Query("hash") hash: String): Observable<ResponseDTO>
}