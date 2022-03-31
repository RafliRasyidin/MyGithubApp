package com.rasyidin.mygithubapp.search.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LicenseResponse(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("spdx_id")
    val spdxId: String? = null,

    @field:SerializedName("url")
    val url: String? = null,
)
