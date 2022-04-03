package com.rasyidin.mygithubapp.profile.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("repos_url")
	var reposUrl: String? = null,

	@field:SerializedName("following_url")
	var followingUrl: String? = null,

	@field:SerializedName("twitter_username")
	var twitterUsername: String? = null,

	@field:SerializedName("bio")
	var bio: String? = null,

	@field:SerializedName("created_at")
	var createdAt: String? = null,

	@field:SerializedName("login")
	var username: String? = null,

	@field:SerializedName("blog")
	var blog: String? = null,

	@field:SerializedName("subscriptions_url")
	var subscriptionsUrl: String? = null,

	@field:SerializedName("updated_at")
	var updatedAt: String? = null,

	@field:SerializedName("company")
	var company: String? = null,

	@field:SerializedName("id")
	var id: Int? = null,

	@field:SerializedName("public_repos")
	var publicRepos: Int? = null,

	@field:SerializedName("email")
	var email: String? = null,

	@field:SerializedName("organizations_url")
	var organizationsUrl: String? = null,

	@field:SerializedName("starred_url")
	var starredUrl: String? = null,

	@field:SerializedName("followers_url")
	var followersUrl: String? = null,

	@field:SerializedName("url")
	var url: String? = null,

	@field:SerializedName("received_events_url")
	var receivedEventsUrl: String? = null,

	@field:SerializedName("followers")
	var followers: Int? = null,

	@field:SerializedName("avatar_url")
	var avatarUrl: String? = null,

	@field:SerializedName("following")
	var following: Int? = null,

	@field:SerializedName("name")
	var name: String? = null,

	@field:SerializedName("location")
	var location: String? = null,

	@field:SerializedName("type")
	var type: String? = null,
)
