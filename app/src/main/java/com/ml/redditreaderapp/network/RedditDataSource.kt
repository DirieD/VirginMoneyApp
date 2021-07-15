package com.ml.redditreaderapp.network

import androidx.paging.PagingSource
import com.ml.redditreaderapp.domain.RedditPost
import com.ml.redditreaderapp.domain.toRedditPost
import retrofit2.HttpException
import java.io.IOException

class RedditDataSource(private val redditApi: RedditApi) :
    PagingSource<String, RedditPost>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, RedditPost> {
         return try {
            val response = redditApi.getPosts(params.loadSize)
            val listing = response.data
            val posts = listing.children.map { it.data.toRedditPost() }

            LoadResult.Page(
                posts,
                listing.before,
                listing.after
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }

    override val keyReuseSupported: Boolean = true
}