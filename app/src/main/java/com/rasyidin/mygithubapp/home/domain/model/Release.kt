package com.rasyidin.mygithubapp.home.domain.model

import com.rasyidin.mygithubapp.profile.domain.model.User

data class Release(
    val author: User? = null,
    val tagName: String? = null,
    val createdAt: String? = null,
    val body: String? = null,
    val name: String? = null,
    val id: Int? = null,
    val publishedAt: String? = null,
)
