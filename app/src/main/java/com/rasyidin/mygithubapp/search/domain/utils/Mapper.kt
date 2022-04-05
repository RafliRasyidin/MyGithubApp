package com.rasyidin.mygithubapp.search.domain.utils

import com.rasyidin.mygithubapp.home.domain.utils.toUser
import com.rasyidin.mygithubapp.search.data.source.remote.response.LicenseResponse
import com.rasyidin.mygithubapp.search.data.source.remote.response.RepositoryResponse
import com.rasyidin.mygithubapp.search.domain.model.License
import com.rasyidin.mygithubapp.search.domain.model.Repository

fun RepositoryResponse.toRepository() = Repository(
    stargazersCount = this.stargazersCount,
    pushedAt = this.pushedAt,
    language = this.language,
    labelsUrl = this.labelsUrl,
    score = this.score,
    id = this.id,
    forks = this.forks,
    forksUrl = this.forksUrl,
    visibility = this.visibility,
    licenseResponse = this.licenseResponse?.toLicense(),
    fullName = this.fullName,
    size = this.size,
    collaboratorsUrl = this.collaboratorsUrl,
    cloneUrl = this.cloneUrl,
    name = this.name,
    defaultBranch = this.defaultBranch,
    contributorsUrl = this.contributorsUrl,
    openIssuesCount = this.openIssuesCount,
    description = this.description,
    createdAt = this.createdAt,
    watchers = this.watchers,
    updatedAt = this.updatedAt,
    owner = this.owner?.toUser(),
    downloadsUrl = this.downloadsUrl,
    htmlUrl = this.htmlUrl,
    fork = this.fork,
    openIssues = this.openIssues,
    watchersCount = this.watchersCount,
    forksCount = this.forksCount
)

fun LicenseResponse.toLicense() = License(
    name, spdxId, url
)

fun List<RepositoryResponse>.toRepositories(): List<Repository> {
    val data = ArrayList<Repository>()
    this.map {
        val repository = Repository(
            stargazersCount = it.stargazersCount,
            pushedAt = it.pushedAt,
            language = it.language,
            labelsUrl = it.labelsUrl,
            score = it.score,
            id = it.id,
            forks = it.forks,
            forksUrl = it.forksUrl,
            visibility = it.visibility,
            licenseResponse = it.licenseResponse?.toLicense(),
            fullName = it.fullName,
            size = it.size,
            collaboratorsUrl = it.collaboratorsUrl,
            cloneUrl = it.cloneUrl,
            name = it.name,
            defaultBranch = it.defaultBranch,
            contributorsUrl = it.contributorsUrl,
            openIssues = it.openIssues,
            openIssuesCount = it.openIssuesCount,
            description = it.description,
            createdAt = it.createdAt,
            watchers = it.watchers,
            updatedAt = it.updatedAt,
            owner = it.owner?.toUser(),
            downloadsUrl = it.downloadsUrl,
            htmlUrl = it.htmlUrl,
            fork = it.fork,
            watchersCount = it.watchersCount,
            forksCount = it.forksCount
        )
        data.add(repository)
    }
    return data
}