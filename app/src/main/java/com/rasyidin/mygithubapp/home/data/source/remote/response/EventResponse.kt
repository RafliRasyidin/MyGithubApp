package com.rasyidin.mygithubapp.home.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.rasyidin.mygithubapp.profile.data.source.remote.response.UserResponse
import com.rasyidin.mygithubapp.search.data.source.remote.response.RepositoryResponse

data class EventResponse(

    @field:SerializedName("EventResponse")
    val eventResponse: List<EventResponseItem?>? = null
)

data class PayloadResponse(

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("action")
    val action: String? = null,

    @field:SerializedName("forkee")
    val forkeeResponse: ForkeeResponse? = null,

    @field:SerializedName("release")
    val releaseResponse: ReleaseResponse? = null,

    @field:SerializedName("member")
    val member: UserResponse? = null
)

data class OrgResponse(

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("login")
    val username: String? = null,

    @field:SerializedName("url")
    val url: String? = null
)

data class EventResponseItem(

    @field:SerializedName("actor")
    val actorResponse: ActorResponse? = null,

    @field:SerializedName("payload")
    val payloadResponse: PayloadResponse? = null,

    @field:SerializedName("repo")
    var repoResponse: RepositoryResponse? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("org")
    val orgResponse: OrgResponse? = null
)


