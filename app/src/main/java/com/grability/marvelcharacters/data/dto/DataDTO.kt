package com.grability.marvelcharacters.data.dto

import com.google.gson.annotations.SerializedName

class DataDTO {
    @SerializedName("offset")
    var offset: Int? = 0
    @SerializedName("limit")
    var limit: Int? = 0
    @SerializedName("total")
    var total: Int? = 0
    @SerializedName("count")
    var count: Int? = 0
    @SerializedName("results")
    var results: List<ResultsDTO>? = null
}