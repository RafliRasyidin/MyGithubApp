package com.rasyidin.mygithubapp.home.domain.model

import com.rasyidin.mygithubapp.profile.domain.model.User

data class Forkee(
    val allowForking: Boolean? = null,
    val stargazersCount: Int? = null,
    val pushedAt: String? = null,
    val id: Int? = null,
    val fullName: String? = null,
    val cloneUrl: String? = null,
    val name: String? = null,
    val description: String? = null,
    val createdAt: String? = null,
    val watchers: Int? = null,
    val updatedAt: String? = null,
    val owner: User? = null,
    val url: String? = null,
    val forksCount: Int? = null
)
