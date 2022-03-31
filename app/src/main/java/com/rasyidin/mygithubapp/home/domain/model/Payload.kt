package com.rasyidin.mygithubapp.home.domain.model

import com.rasyidin.mygithubapp.profile.domain.model.User

data class Payload(
    val description: String? = null,
    val action: String? = null,
    val forkee: Forkee? = null,
    val release: Release? = null,
    val member: User? = null
)
