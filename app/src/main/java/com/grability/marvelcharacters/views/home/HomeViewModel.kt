package com.grability.marvelcharacters.views.home

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grability.marvelcharacters.R
import com.grability.marvelcharacters.data.interactor.IMarvelInteractor
import com.grability.marvelcharacters.data.model.ResultsModel
import com.grability.marvelcharacters.di.DaggerMarvelComponent
import com.grability.marvelcharacters.utils.RUtil
import com.grability.marvelcharacters.views.base.SingleLiveEvent
import java.net.ConnectException
import javax.inject.Inject

class HomeViewModel: ViewModel() {

    @Inject
    lateinit var interactor: IMarvelInteractor
    var characterList: MutableLiveData<List<ResultsModel>> = MutableLiveData()

    init {
        DaggerMarvelComponent.builder().build().inject(this)
    }

    sealed class ViewEvent {
        class ResponseSuccess: ViewEvent()
        class ResponseError(val error: String?): ViewEvent()
    }

    var singleLiveEvent: SingleLiveEvent<ViewEvent> = SingleLiveEvent()

    @SuppressLint("CheckResult")
    fun getCharacters(ts: Long, apikey: String, hash: String, page: Int) {
        interactor.getCharacters(ts, apikey, hash, page)?.subscribe({
            if(it.code == 200){
                characterList.value = it.dataModel?.results
                singleLiveEvent.value = ViewEvent.ResponseSuccess()
            } else {
                singleLiveEvent.value = ViewEvent.ResponseError(it.status)
            }
        },{
            if(it is ConnectException)
                singleLiveEvent.value = ViewEvent.ResponseError(RUtil.rString(R.string.message_not_connected))
            else
                singleLiveEvent.value = ViewEvent.ResponseError(RUtil.rString(R.string.message_error_ocurred))
        })
    }
}