package com.rasyidin.mygithubapp.search.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SearchRepositoriesResponse(

	@field:SerializedName("items")
	val repositoriesResponse: List<RepositoryResponse?>? = null
)
