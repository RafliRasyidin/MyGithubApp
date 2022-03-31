package com.rasyidin.mygithubapp.home.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ActorResponse(

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("login")
    val username: String? = null,

    @field:SerializedName("url")
    val url: String? = null
)