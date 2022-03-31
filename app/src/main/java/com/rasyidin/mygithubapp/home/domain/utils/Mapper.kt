package com.rasyidin.mygithubapp.home.domain.utils

import com.rasyidin.mygithubapp.home.data.source.remote.response.*
import com.rasyidin.mygithubapp.home.domain.model.*
import com.rasyidin.mygithubapp.profile.data.source.remote.response.UserResponse
import com.rasyidin.mygithubapp.profile.domain.model.User
import com.rasyidin.mygithubapp.search.domain.utils.toRepository

fun EventResponseItem.toEvent() = Event(
    actor = this.actorResponse?.toActor(),
    payload = this.payloadResponse?.toPayload(),
    repo = this.repoResponse?.toRepository(),
    createdAt = this.createdAt,
    id = this.id,
    type = this.type,
    org = this.orgResponse?.toOrg()
)

fun ActorResponse.toActor() = Actor(
    avatarUrl = this.avatarUrl,
    id = this.id,
    username = this.username,
    url = this.url
)

fun PayloadResponse.toPayload() = Payload(
    description = this.description,
    action = this.action,
    forkee = this.forkeeResponse?.toForkee(),
    release = this.releaseResponse?.toRelease(),
    member = this.member?.toUser()
)

fun OrgResponse.toOrg() = Org(
    avatarUrl = this.avatarUrl,
    id = this.id,
    username = this.username,
    url = this.url
)

fun ForkeeResponse.toForkee() = Forkee(
    allowForking = this.allowForking,
    stargazersCount = this.stargazersCount,
    pushedAt = this.pushedAt,
    id = this.id,
    fullName = this.fullName,
    cloneUrl = this.cloneUrl,
    name = this.name,
    description = this.description,
    createdAt = this.createdAt,
    watchers = this.watchers,
    updatedAt = this.updatedAt,
    owner = this.owner?.toUser(),
    url = this.url,
    forksCount = this.forksCount
)

fun ReleaseResponse.toRelease() = Release(
    author = this.author?.toUser(),
    tagName = this.tagName,
    createdAt = this.createdAt,
    body = this.body,
    name = this.name,
    id = this.id,
    publishedAt = this.publishedAt
)

fun UserResponse.toUser() = User(
    reposUrl = this.reposUrl,
    followingUrl = this.followingUrl,
    twitterUsername = this.twitterUsername,
    bio = this.bio,
    createdAt = this.createdAt,
    username = this.username,
    blog = this.blog,
    subscriptionsUrl = this.subscriptionsUrl,
    updatedAt = this.updatedAt,
    company = this.company,
    id = this.id,
    publicRepos = this.publicRepos,
    email = this.email,
    organizationsUrl = this.organizationsUrl,
    starredUrl = this.starredUrl,
    followersUrl = this.followersUrl,
    url = this.url,
    receivedEventsUrl = this.receivedEventsUrl,
    followers = this.followers,
    avatarUrl = this.avatarUrl,
    following = this.following,
    name = this.name,
    location = this.location
)