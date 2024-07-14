package com.nyonz.daredevil.model

import com.google.gson.annotations.SerializedName

data class Connections(
    @SerializedName("group-affiliation")
    val groupaffiliation: String,

    val relatives: String
)