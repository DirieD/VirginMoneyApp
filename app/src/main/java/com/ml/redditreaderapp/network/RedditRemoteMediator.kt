package com.ml.redditreaderapp.network

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ml.redditreaderapp.db.PostDatabase
import com.ml.redditreaderapp.domain.RedditPost
import com.ml.redditreaderapp.domain.toRedditPost
import com.ml.redditreaderapp.domain.RedditKeys
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class RedditRemoteMediator(
    private val redditApi: RedditApi,
    private val redditDatabase: PostDatabase
) : RemoteMediator<Int, RedditPost>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RedditPost>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    getRedditKeys()
                }
            }

            val response = redditApi.getPosts(
                loadSize = state.config.pageSize,
                after = loadKey?.after,
                before = loadKey?.before
            )
            val listing = response.data
            val redditPosts = listing.children.map { it.data.toRedditPost() }

            redditDatabase.withTransaction {
                redditDatabase.keysDao()
                    .saveRedditKeys(RedditKeys(0, listing.after, listing.before))
                redditDatabase.postsDao().savePosts(redditPosts)
            }
            MediatorResult.Success(endOfPaginationReached = listing.after == null)
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }

    private suspend fun getRedditKeys(): RedditKeys? {
        return redditDatabase.keysDao().getRedditKeys().firstOrNull()
    }
}