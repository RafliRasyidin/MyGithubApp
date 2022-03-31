package com.rasyidin.mygithubapp.search.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.rasyidin.mygithubapp.profile.data.source.remote.response.UserResponse

data class SearchUsersResponse(
    @field:SerializedName("items")
    val users: List<UserResponse?>? = null
)
