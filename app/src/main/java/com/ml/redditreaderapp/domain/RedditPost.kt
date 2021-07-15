package com.ml.redditreaderapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class RedditPost(
    @PrimaryKey
    val title : String,
    val author : String,
    val url: String
)