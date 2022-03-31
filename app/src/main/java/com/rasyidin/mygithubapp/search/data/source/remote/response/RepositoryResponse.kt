package com.rasyidin.mygithubapp.search.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.rasyidin.mygithubapp.profile.data.source.remote.response.UserResponse

data class RepositoryResponse(

    @field:SerializedName("stargazers_count")
    val stargazersCount: Int? = null,

    @field:SerializedName("pushed_at")
    val pushedAt: String? = null,

    @field:SerializedName("language")
    val language: String? = null,

    @field:SerializedName("labels_url")
    val labelsUrl: String? = null,

    @field:SerializedName("score")
    val score: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("forks")
    val forks: Int? = null,

    @field:SerializedName("forks_url")
    val forksUrl: String? = null,

    @field:SerializedName("visibility")
    val visibility: String? = null,

    @field:SerializedName("license")
    val licenseResponse: LicenseResponse? = null,

    @field:SerializedName("full_name")
    val fullName: String? = null,

    @field:SerializedName("size")
    val size: Int? = null,

    @field:SerializedName("collaborators_url")
    val collaboratorsUrl: String? = null,

    @field:SerializedName("clone_url")
    val cloneUrl: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("default_branch")
    val defaultBranch: String? = null,

    @field:SerializedName("contributors_url")
    val contributorsUrl: String? = null,

    @field:SerializedName("open_issues_count")
    val openIssuesCount: Int? = null,

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

    @field:SerializedName("downloads_url")
    val downloadsUrl: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("fork")
    val fork: Boolean? = null,

    @field:SerializedName("open_issues")
    val openIssues: Int? = null,

    @field:SerializedName("watchers_count")
    val watchersCount: Int? = null,

    @field:SerializedName("forks_count")
    val forksCount: Int? = null
)
