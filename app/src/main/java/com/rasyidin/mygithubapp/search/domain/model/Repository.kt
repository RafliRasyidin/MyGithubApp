package com.rasyidin.mygithubapp.search.domain.model

import com.rasyidin.mygithubapp.profile.domain.model.User

data class Repository(

    val stargazersCount: Int? = null,
    val pushedAt: String? = null,
    val language: String? = null,
    val labelsUrl: String? = null,
    val score: Int? = null,
    val id: Int? = null,
    val forks: Int? = null,
    val forksUrl: String? = null,
    val visibility: String? = null,
    val licenseResponse: License? = null,
    val fullName: String? = null,
    val size: Int? = null,
    val collaboratorsUrl: String? = null,
    val cloneUrl: String? = null,
    val name: String? = null,
    val defaultBranch: String? = null,
    val contributorsUrl: String? = null,
    val openIssuesCount: Int? = null,
    val description: String? = null,
    val createdAt: String? = null,
    val watchers: Int? = null,
    val updatedAt: String? = null,
    val owner: User? = null,
    val downloadsUrl: String? = null,
    val fork: Boolean? = null,
    val openIssues: Int? = null,
    val watchersCount: Int? = null,
    val forksCount: Int? = null,
    val htmlUrl: String? = null
)
