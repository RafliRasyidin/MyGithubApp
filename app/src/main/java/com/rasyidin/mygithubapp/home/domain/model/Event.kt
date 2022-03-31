package com.rasyidin.mygithubapp.home.domain.model

import com.rasyidin.mygithubapp.search.domain.model.Repository

data class Event(
    val actor: Actor? = null,
    val payload: Payload? = null,
    val repo: Repository? = null,
    val createdAt: String? = null,
    val id: String? = null,
    val type: String? = null,
    val org: Org? = null
)
