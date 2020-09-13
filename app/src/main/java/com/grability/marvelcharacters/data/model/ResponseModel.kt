package com.grability.marvelcharacters.data.model

import com.google.gson.annotations.SerializedName

class ResponseModel {
    @SerializedName("code")
    var code: Int? = 0
    @SerializedName("status")
    var status: String? = null
    @SerializedName("data")
    var dataModel: DataModel? = null
}