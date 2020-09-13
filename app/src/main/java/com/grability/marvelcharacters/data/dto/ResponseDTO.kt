package com.grability.marvelcharacters.data.dto

import com.google.gson.annotations.SerializedName

class ResponseDTO {
    @SerializedName("code")
    var code: Int? = 0
    @SerializedName("status")
    var status: String? = null
    @SerializedName("data")
    var dataDTO: DataDTO? = null
}