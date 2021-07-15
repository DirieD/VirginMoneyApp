package com.ml.redditreaderapp.domain

import com.google.gson.annotations.SerializedName

data class PostData(
    @SerializedName("author") val author : String,
    @SerializedName("title") val title : String,
    @SerializedName("url") val url: String
)

fun PostData.toRedditPost() =
    RedditPost(this.title, this.author, this.url)