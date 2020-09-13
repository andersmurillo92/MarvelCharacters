package com.grability.marvelcharacters.data.model

import com.google.gson.annotations.SerializedName

class ResultsModel {
    @SerializedName("id")
    var id: Int? = 0
    @SerializedName("name")
    var name: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("thumbnail")
    var thumbnailModel: ThumbnailModel? = null
}