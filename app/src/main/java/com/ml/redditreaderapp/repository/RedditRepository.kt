package com.ml.redditreaderapp.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ml.redditreaderapp.db.PostDatabase
import com.ml.redditreaderapp.domain.RedditPost
import com.ml.redditreaderapp.network.RedditApi
import com.ml.redditreaderapp.network.RedditDataSource
import com.ml.redditreaderapp.network.RedditRemoteMediator
import kotlinx.coroutines.flow.Flow

class RedditRepository(
    private val redditApi: RedditApi,
    private val redditDatabase: PostDatabase
) {

    @OptIn(ExperimentalPagingApi::class)
    fun fetchPosts(): Flow<PagingData<RedditPost>> {
        return Pager(
            PagingConfig(
                pageSize = 40,
                enablePlaceholders = false,
                prefetchDistance = 3),

            remoteMediator = RedditRemoteMediator(redditApi, redditDatabase),
            pagingSourceFactory = { redditDatabase.postsDao().getPosts() }

        ).flow
    }
}