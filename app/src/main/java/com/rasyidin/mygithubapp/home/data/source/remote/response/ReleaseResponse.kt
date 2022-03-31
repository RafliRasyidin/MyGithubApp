package com.rasyidin.mygithubapp.home.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.rasyidin.mygithubapp.profile.data.source.remote.response.UserResponse

data class ReleaseResponse(

    @field:SerializedName("author")
    val author: UserResponse? = null,

    @field:SerializedName("tag_name")
    val tagName: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("body")
    val body: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("published_at")
    val publishedAt: String? = null,

    )