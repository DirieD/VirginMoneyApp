package com.ml.redditreaderapp.network

import com.ml.redditreaderapp.domain.Response
import com.ml.redditreaderapp.network.NetworkConstants.GET_POSTS_ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {

    @GET(GET_POSTS_ENDPOINT)
    suspend fun getPosts(
        @Query("limit") loadSize: Int = 0,
        @Query("after") after: String? = null,
        @Query("before") before: String? = null
    ): Response
}