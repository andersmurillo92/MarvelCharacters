package com.grability.marvelcharacters.data.interactor

import com.grability.marvelcharacters.data.dto.DataDTO
import com.grability.marvelcharacters.data.dto.ResultsDTO
import com.grability.marvelcharacters.data.model.DataModel
import com.grability.marvelcharacters.data.model.ResponseModel
import com.grability.marvelcharacters.data.model.ResultsModel
import com.grability.marvelcharacters.data.model.ThumbnailModel
import com.grability.marvelcharacters.data.repository.IMarvelRepository
import com.grability.marvelcharacters.di.DaggerMarvelComponent
import com.grability.marvelcharacters.di.MarvelModule
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface IMarvelInteractor {
    fun getCharacters(ts: Long, apikey: String, hash: String): Observable<ResponseModel>?
}

class MarvelInteractor: IMarvelInteractor {

    @Inject
    lateinit var repository: IMarvelRepository

    init {
        DaggerMarvelComponent.builder()
            .marvelModule(MarvelModule())
            .build().inject(this)
    }

    override fun getCharacters(ts: Long, apikey: String, hash: String): Observable<ResponseModel>? {
        return repository.getCharacters(ts, apikey, hash)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.flatMap {
                val responseModel = ResponseModel().apply {
                    this.code = it.code
                    this.status = it.status
                    this.dataModel = convertDataDtoToModel(it.dataDTO)
                }
                Observable.just(responseModel)
            }
    }

    private fun convertDataDtoToModel(dataDTO: DataDTO?): DataModel {
        val result = DataModel().apply {
            this.offset = dataDTO?.offset
            this.limit = dataDTO?.limit
            this.total = dataDTO?.total
            this.count = dataDTO?.count
            this.results = convertResultsDtoToModel(dataDTO?.results)
        }
        return result
    }

    private fun convertResultsDtoToModel(list: List<ResultsDTO>?): List<ResultsModel> {
        val result= arrayListOf<ResultsModel>()
        list?.forEach {
            val resultsModel = ResultsModel().apply {
                this.id = it.id
                this.name = it.name
                this.description = it.description
                this.thumbnailModel = ThumbnailModel().apply {
                    this.path = it.thumbnailDTO?.path
                    this.extension = it.thumbnailDTO?.extension
                }
            }
            result.add(resultsModel)
        }
        return result
    }
}