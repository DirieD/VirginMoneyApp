package com.ml.redditreaderapp.domain

import com.google.gson.annotations.SerializedName

data class Response (
	@SerializedName("kind") val kind : String,
	@SerializedName("data") val data : Data
)