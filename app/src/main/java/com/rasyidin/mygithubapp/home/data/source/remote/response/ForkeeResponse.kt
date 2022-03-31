package com.rasyidin.mygithubapp.home.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.rasyidin.mygithubapp.profile.data.source.remote.response.UserResponse

data class ForkeeResponse(

    @field:SerializedName("allow_forking")
    val allowForking: Boolean? = null,

    @field:SerializedName("stargazers_count")
    val stargazersCount: Int? = null,

    @field:SerializedName("pushed_at")
    val pushedAt: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("full_name")
    val fullName: String? = null,

    @field:SerializedName("clone_url")
    val cloneUrl: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("watchers")
    val watchers: Int? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("owner")
    val owner: UserResponse? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("forks_count")
    val forksCount: Int? = null
)