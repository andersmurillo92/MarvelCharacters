package com.grability.marvelcharacters.data.dto

import com.google.gson.annotations.SerializedName

class ResultsDTO {
    @SerializedName("id")
    var id: Int? = 0
    @SerializedName("name")
    var name: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("thumbnail")
    var thumbnailDTO: ThumbnailDTO? = null
}