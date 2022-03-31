package com.rasyidin.mygithubapp.profile.domain.usecase

data class ProfileUseCase(
    val getAuthUser: GetAuthUser,
    val getDetailUser: GetDetailUser,
    val getAuthUserFollowers: GetAuthUserFollowers,
    val getAuthUserFollowing: GetAuthUserFollowing,
    val followUser: FollowUser,
    val unfollowUser: UnfollowUser,
    val getUserFollowers: GetUserFollowers,
    val getUserFollowing: GetUserFollowing
)
